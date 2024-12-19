package com.jiangdk.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.dto.StockDTO;
import com.jiangdk.pms.pojo.entity.Sku;
import com.jiangdk.pms.pojo.form.SkuForm;
import com.jiangdk.pms.pojo.vo.SkuVO;

public interface SkuService extends IService<Sku>{
    SkuDTO getSkuById(Long skuId);
    /**
     * 下单减库存
     */
    void lockStock(StockDTO stockDTO);

    /**
     * 取消订单释放库存
     */
    void unlockStock(String orderSn);

    /**更新商品状态
     * @param skuId
     * @param status
     */
    void updateStatus(Long skuId, Integer status);

    void updateSkuById(SkuForm skuForm);

    SkuVO getSkuDetailById(Long skuId);
}
