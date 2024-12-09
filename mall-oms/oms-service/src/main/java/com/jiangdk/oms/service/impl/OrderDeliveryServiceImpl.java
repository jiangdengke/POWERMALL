package com.jiangdk.oms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.oms.mapper.OrderDeliveryMapper;
import com.jiangdk.oms.pojo.entity.OrderDelivery;
import com.jiangdk.oms.service.OrderDeliveryService;
@Service
public class OrderDeliveryServiceImpl extends ServiceImpl<OrderDeliveryMapper, OrderDelivery> implements OrderDeliveryService{

}
