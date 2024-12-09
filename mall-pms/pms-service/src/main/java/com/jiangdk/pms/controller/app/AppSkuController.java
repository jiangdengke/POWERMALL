package com.jiangdk.pms.controller.app;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;

/**
 * @author: JiangDk
 * @date: 2024/12/9 16:57
 * @description:
 */
@RestController
@RequestMapping("/api/pms/sku")
public class AppSkuController {
    @Autowired
    private SkuService skuService;
    @GetMapping("/info")
    Result<SkuDTO> getSkuById(@RequestParam("skuId") Long skuId){
        return Result.success(skuService.getSkuById(skuId));
    }
}
