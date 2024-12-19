package com.jiangdk.pms.controller.admin;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.pojo.dto.SkuStatusDTO;
import com.jiangdk.pms.pojo.form.SkuForm;
import com.jiangdk.pms.pojo.vo.SkuVO;
import com.jiangdk.pms.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端/商品SKU管理
 */
@RestController
@RequestMapping("/api-admin/pms/sku")
public class SkuController {
    @Autowired
    private SkuService skuService;
    /**
     * 更新商品状态
     */
    @PostMapping
    public Result updateStatus
    (
            @RequestBody SkuStatusDTO skuStatusDTO
    ) {
        Long skuId = skuStatusDTO.getSkuId();
        Integer status = skuStatusDTO.getStatus();
        skuService.updateStatus(skuId, status);
        return Result.success();
    }
    /**
     * 删除sku
     */
    @DeleteMapping("/{skuId}")
    public Result deleteSku(@PathVariable("skuId") Long skuId) {
        skuService.removeById(skuId);
        return Result.success();
    }
    /**
     * 更新sku
     */
    @PutMapping("/{skuId}")
    public Result updateSkuById(@PathVariable Long skuId, @RequestBody SkuForm skuForm){
        skuForm.setId(skuId);
        skuService.updateSkuById(skuForm);
        return Result.success();
    }
    /**
     * 获取sku详情
     */
    @GetMapping("/{skuId}")
    public Result<SkuVO> getById(
            @PathVariable("skuId") Long skuId
    ){
        return Result.success(skuService.getSkuDetailById(skuId));
    }

}
