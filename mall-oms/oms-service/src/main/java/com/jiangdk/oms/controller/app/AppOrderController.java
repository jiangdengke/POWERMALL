package com.jiangdk.oms.controller.app;

import com.jiangdk.common.result.Result;
import com.jiangdk.oms.pojo.vo.OrderVO;
import com.jiangdk.oms.service.OrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

/**
 * 应用端/订单
 */
@RestController
@RequestMapping("/api/oms/order")
public class AppOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 确认订单【从商品详情页下单】
     *
     * @return
     */
    @GetMapping("/confirm")
    public Result<OrderVO> confirmOrder(
            @NotNull Long skuId,
            @NotNull Integer count) {
        return Result.success(orderService.orderConfirm(skuId, count));
    }

    /**
     * 确认订单【从购物车下单】
     *
     * @return
     */
    @GetMapping("/confirmCart")
    public Result<OrderVO> confirmOrder() {
        return Result.success(orderService.orderConfirm());
    }
}
