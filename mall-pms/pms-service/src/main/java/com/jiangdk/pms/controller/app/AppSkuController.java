package com.jiangdk.pms.controller.app;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.dto.StockDTO;
import com.jiangdk.pms.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;

/**
 * 应用端/商品
 */
@RestController
@RequestMapping("/api/pms/sku")
public class AppSkuController {
    @Autowired
    private SkuService skuService;

    /**
     * 获取下单数据
     * @param skuId
     * @return
     */
    @GetMapping("/info")
    Result<SkuDTO> getSkuById(@RequestParam("skuId") Long skuId){
        return Result.success(skuService.getSkuById(skuId));
    }
    /**
     * 扣减库存
     * @param stockDTO
     * @return
     */
    @PutMapping("/lockStock")
    public Result lockStock(@RequestBody StockDTO stockDTO) {
        skuService.lockStock(stockDTO);
        return Result.success();
    }

    /**
     * 取消订单扣减库存
     * @param orderSn
     * @return
     */
    @PutMapping("/unlockStock")
    public Result unlockStock(String orderSn) {
        return Result.success();
    }
}
