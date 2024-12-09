package com.jiangdk.pms.controller.app;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 应用端/购物车
 */
@RestController
@RequestMapping("/api/pms/cart")
@Validated
public class AppCartController {
    @Autowired
    private CartService cartService;

    /**
     * 获取购物车列表
     * @return
     */
    @GetMapping
    public Result list() {
        return Result.success(cartService.getAll());
    }

    /**
     * 添加商品到购物车
     * @param skuId
     * @param count
     * @return
     */
    @PostMapping("/add")
    public Result add(
            @NotNull Long skuId, @NotNull Integer count) {
        cartService.add(skuId,count);
        return Result.success();
    }

    /**
     * 修改购物车商品数量
     * @param skuId
     * @param count
     * @return
     */
    @PutMapping("/count")
    public Result updateCount(Long skuId, Integer count) {
        cartService.updateCount(skuId, count);
        return Result.success();
    }

    /**
     * 修改购物车商品选中状态
     * @param skuId
     * @param checked
     * @return
     */
    @PutMapping("/checked")
    public Result checked(Long skuId, Boolean checked) {
        cartService.checked(skuId,checked);
        return Result.success();
    }

    /**
     * 全选/全不选
     * @param checked
     * @return
     */
    @PutMapping("/checkedAll")
    public Result checkedAll(@RequestParam("skuIds") List<Long> skuIds,Boolean checked) {
        cartService.checkedAll(skuIds,checked);
        return Result.success();
    }

    /**
     * 删除购物车商品
     * @param skuId
     * @return
     */
    @DeleteMapping("/del")
    public Result deleteById(Long skuId) {
        cartService.deleteById(skuId);
        return Result.success();
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping
    public Result deleteAll() {
        cartService.deleteAll();
        return Result.success();
    }
}
