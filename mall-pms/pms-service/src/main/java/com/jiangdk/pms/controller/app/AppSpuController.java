package com.jiangdk.pms.controller.app;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.pojo.vo.SpuVO;
import com.jiangdk.pms.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
