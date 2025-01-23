package com.jiangdk.pms.controller.app;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.pojo.vo.SpuVO;
import com.jiangdk.pms.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用端/商品
 */
@RestController
@RequestMapping("/api/pms/goods")
public class AppSpuController {
    @Autowired
    private SpuService spuService;
    /**
     * 商品详情
     * @param spuId
     * @return
     */
    @GetMapping("/{id}")
    public Result<SpuVO> getSpuDetail(
             @PathVariable("id") Long spuId) {
        return Result.success(spuService.getSpuById(spuId));
    }
    /**
     * 根据分类id获取对应商品
     */
    @GetMapping("/list")
    public Result<List<SpuVO>> getSpuList
    (
            @RequestParam("categoryId") Long categoryId
    ) {
        return Result.success(spuService.getSpuListByCategoryId(categoryId));
    }
    /**
     * 获取所有商品
     */
    @GetMapping("/all")
    public Result<List<SpuVO>> getAllSpu() {
        return Result.success(spuService.getSpuList());
    }
}
