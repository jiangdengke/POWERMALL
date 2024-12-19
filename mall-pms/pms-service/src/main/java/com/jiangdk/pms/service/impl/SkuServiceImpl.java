package com.jiangdk.pms.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.dto.StockDTO;
import com.jiangdk.pms.mapper.SkuMapper;
import com.jiangdk.pms.pojo.entity.Sku;
import com.jiangdk.pms.pojo.form.SkuForm;
import com.jiangdk.pms.pojo.vo.SkuVO;
import com.jiangdk.pms.service.SkuService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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

    /**
     * 更新商品状态
     *
     * @param skuId
     * @param status
     */
    @Override
    public void updateStatus(Long skuId, Integer status) {
        Sku sku = baseMapper.selectById(skuId);
        if (sku == null){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"商品信息不存在");
        }
        sku.setStatus(status);
        baseMapper.updateById(sku);
    }

    @Override
    public void updateSkuById(SkuForm skuForm) {
        Long id = skuForm.getId();
        Sku oldSku = baseMapper.selectById(id);
        if (oldSku == null){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"该商品详情未找到");
        }
        BeanUtils.copyProperties(skuForm,oldSku);
        this.baseMapper.updateById(oldSku);
    }

    /**
     * 获取商品详情
     * @param skuId
     */
    @Override
    public SkuVO getSkuDetailById(Long skuId) {
        Sku sku = baseMapper.selectById(skuId);
        if (sku == null){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"商品信息不存在");
        }
        SkuVO skuVO = new SkuVO();
        BeanUtils.copyProperties(sku,skuVO);
        return skuVO;
    }


}
