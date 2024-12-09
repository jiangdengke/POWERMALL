package com.jiangdk.oms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.oms.pojo.entity.OrderItem;
import com.jiangdk.oms.mapper.OrderItemMapper;
import com.jiangdk.oms.service.OrderItemService;
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService{

}
