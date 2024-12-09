package com.jiangdk.pms.service;

import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.pojo.entity.Sku;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SkuService extends IService<Sku>{
    SkuDTO getSkuById(Long skuId);
}
