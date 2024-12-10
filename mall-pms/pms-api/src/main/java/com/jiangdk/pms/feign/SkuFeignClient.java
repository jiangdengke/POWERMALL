package com.jiangdk.pms.feign;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.dto.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: JiangDk
 * @date: 2024/12/9 16:52
 * @description:
 */
@FeignClient(name = "pms-service")
public interface SkuFeignClient {
    /**
     * 获取商品库存信息
     * @param skuId
     * @return
     */
    @GetMapping("/api/pms/sku/info")
    Result<SkuDTO> getSkuById(@RequestParam("skuId") Long skuId);
    /**
     * 下单扣减库存
     */
    @PutMapping("/api/pms/sku/lockStock")
    Result lockStock(@RequestBody StockDTO stockDTO);

    /**
     * 订单取消释放库存
     */
    @PutMapping("/api/pms/sku/unlockStock")
    Result unlockStock(@RequestParam("orderSn") String orderSn);
}
