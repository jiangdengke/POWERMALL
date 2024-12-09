package com.jiangdk.pms.feign;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.dto.SkuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
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
}
