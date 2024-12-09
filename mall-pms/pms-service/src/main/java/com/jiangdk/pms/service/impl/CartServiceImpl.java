package com.jiangdk.pms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpStatus;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.pms.mapper.SkuMapper;
import com.jiangdk.pms.mapper.SpuMapper;
import com.jiangdk.pms.pojo.entity.Sku;
import com.jiangdk.pms.pojo.entity.Spu;
import com.jiangdk.pms.pojo.vo.CartItemVO;
import com.jiangdk.pms.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: JiangDk
 * @date: 2024/12/7 19:57
 * @description:
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SpuMapper spuMapper;
    // 购物车的key，这里先写死
    private String key() {
        Long userId = 1L;
        return "cart:" + userId;
    }
    /**
     * 将商品添加到购物车
     *
     * @param skuId 商品库存ID
     * @param count 添加的数量
     */
    @Override
    public void add(Long skuId, Integer count) {
        Sku sku = skuMapper.selectById(skuId);
        if (sku == null || sku.getStatus() == 0){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"商品不存在或已下架");
        }
        String goodsKey = key() + ":" + skuId;
        // 增加count数
        redisTemplate.opsForHash().increment(goodsKey,"count",count);
        // 更新商品的score值
        redisTemplate.opsForZSet().add(key(), goodsKey, System.currentTimeMillis());
        // 设置商品的选中状态
        redisTemplate.opsForHash().put(goodsKey, "checked", true);
        // 保证只有在首次添加时才初始化商品价格
        if(redisTemplate.hasKey(key())){
            // 设置商品加入购物车时的价格
            redisTemplate.opsForHash().put(goodsKey, "originalPrice", sku.getPrice());
        }

    }

    /**
     * 更新购物车中商品的数量
     *
     * @param skuId 商品id
     * @param count 更新后的数量
     */
    @Override
    public void updateCount(Long skuId, Integer count) {
        String goodsKey = key() + ":" + skuId;
        if (redisTemplate.hasKey(goodsKey)){
            redisTemplate.opsForHash().put(goodsKey,"count",count);
        }
    }

    /**
     * 查看购物车
     *
     * @return
     */
    @Override
    public List<CartItemVO> getAll() {
        // 获取redis购物车商品数据
        Set<ZSetOperations.TypedTuple<String>> set = redisTemplate.opsForZSet().rangeByScoreWithScores(key(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        if (CollectionUtil.isEmpty(set)){
            return Collections.emptyList();
        }
        Map<Long,Double> skuScores = new HashMap<>();
        set.forEach(item->{
            String value = item.getValue();
            Double score = item.getScore();
            Long skuId = Long.parseLong(value.substring(value.lastIndexOf(":")+1));
            skuScores.put(skuId,score);
        });
        // 获取mysql数据库商品数据
        Set<Long> skuIds = skuScores.keySet();
        List<Long> skuIdList = new ArrayList<>(skuIds);
        List<CartItemVO> cartItemVOList = skuMapper.selectCartItemByIds(skuIdList);
        for (CartItemVO cartItemVO: cartItemVOList){
            String goodKey = key()+":"+cartItemVO.getSkuId();
            Map<String,Object> map = redisTemplate.opsForHash().entries(goodKey); // 拿到所有的field-value
            cartItemVO.setChecked((Boolean) map.get("checked"));
            cartItemVO.setCount((Integer) map.get("count"));
            cartItemVO.setOriginalPrice((Integer) map.get("originalPrice"));
            // 设置score
            cartItemVO.setScore(skuScores.get(cartItemVO.getSkuId()));
        }
        // 根据score降序排序
        cartItemVOList.sort(Comparator.comparing(CartItemVO::getScore).reversed());
        // 合并数据并返回
        return cartItemVOList;
    }

    /**
     * 选中/取消选中
     *
     * @param skuId
     * @param checked
     */
    @Override
    public void checked(Long skuId, Boolean checked) {
        String goodsKey = key() + ":" + skuId;
        if (redisTemplate.hasKey(goodsKey)){
            redisTemplate.opsForHash().put(goodsKey,"checked",checked);
        }
    }

    /**
     * 全选/取消全选
     *
     * @param checked
     */
    @Override
    public void checkedAll(List<Long> skuIds,Boolean checked) {
        for (Long skuId: skuIds){
            this.checked(skuId,checked);
        }
    }

    /**
     * 删除购物车商品
     *
     * @param skuId
     */
    @Override
    public void deleteById(Long skuId) {
        String goodsKey = key() + ":" + skuId;
        redisTemplate.delete(goodsKey);
        redisTemplate.opsForZSet().remove(key(),goodsKey);
    }

    /**
     * 清空购物车
     */
    @Override
    public void deleteAll() {
        Set goodKeysList = redisTemplate.opsForZSet().range(key(), 0, -1);
        redisTemplate.delete(goodKeysList);
        redisTemplate.delete(key());
    }
}
