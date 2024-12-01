package com.jiangdk.pms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.pms.mapper.SkuMapper;
import com.jiangdk.pms.pojo.entity.Sku;
import com.jiangdk.pms.service.SkuService;
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService{

}
