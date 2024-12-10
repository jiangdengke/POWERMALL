package com.jiangdk.pms.service.impl;

import cn.hutool.http.HttpStatus;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.common.result.Result;
import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.dto.StockDTO;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.pms.mapper.SkuMapper;
import com.jiangdk.pms.pojo.entity.Sku;
import com.jiangdk.pms.service.SkuService;

import java.time.Duration;
import java.util.List;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public SkuDTO getSkuById(Long skuId) {
        SkuDTO skuDTO = this.baseMapper.selectSkuById(skuId);
        return skuDTO;
    }

    /**
     * 下单减库存
     *
     * @param stockDTO
     */
    @Override
    public void lockStock(StockDTO stockDTO) {
        /**
         * 用synchronized对商品id进行加锁,分布式环境下仍会出现超卖现象
         */
/*        stockDTO.getLockedSkus().forEach(item -> {
            synchronized (item.getSkuId()){ //锁的是item.getSkuId()
                // 查询库存
                Sku sku = this.getById(item.getSkuId());
                // 校验库存
                if (sku.getStock() < item.getCount()){
                    throw new BizException(HttpStatus.HTTP_BAD_REQUEST,sku.getName()+":库存不足");
                }
                // 更新商品库存
                this.baseMapper.lockStock(item.getSkuId(), item.getCount());
            }
        });*/
        /**
         * 使用分布式锁解决超卖--redisson
         */
        stockDTO.getLockedSkus().forEach(item -> {
            // 获取分布式锁
            RLock lock = redissonClient.getLock("stock:" + item.getSkuId());
            try{
                // 加锁
                lock.lock();
                // 查询库存
                Sku sku = this.getById(item.getSkuId());
                // 校验库存
                if (sku.getStock() < item.getCount()) {
                    throw new BizException(HttpStatus.HTTP_BAD_REQUEST, sku.getName() + ":库存不足");
                }
                // 更新商品库存
                this.baseMapper.lockStock(item.getSkuId(), item.getCount());
            }finally {
                lock.unlock();
            }
        });
        // 将下单信息缓存到redis，供取消订单释放库存使用
        redisTemplate.opsForValue().set("order:"+stockDTO.getOrderSn(),
                stockDTO.getLockedSkus(), Duration.ofMinutes(35));
    }

    /**
     * 取消订单释放库存
     *
     * @param orderSn 订单编号
     */
    @Override
    public void unlockStock(String orderSn) {
        // 从redis获取商品购买信息
        List<StockDTO.LockedSku> lockedSkus = (List<StockDTO.LockedSku>) redisTemplate.opsForValue().get("order:" + orderSn);
        // 返还库存
        lockedSkus.forEach(lockedSku -> {
            this.baseMapper.unLockStock(lockedSku.getSkuId(),lockedSku.getCount());
        });
        // 从redis删除对应缓存
        redisTemplate.delete("order:"+orderSn);
    }
}
