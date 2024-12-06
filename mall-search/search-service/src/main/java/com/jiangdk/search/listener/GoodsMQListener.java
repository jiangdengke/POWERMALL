package com.jiangdk.search.listener;


import com.jiangdk.pms.dto.GoodsDTO;
import com.jiangdk.search.document.GoodsDoc;
import com.jiangdk.search.service.GoodsService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: JiangDk
 * @date: 2024/12/6 17:57
 * @description:
 */
@Component
public class GoodsMQListener {
    @Autowired
    private GoodsService goodsService;

    /**
     * 监听商品保存
     *
     * @param
     * @param channel
     * @param message
     */
    @RabbitListener(queues = {"pms.goods.save", "pms.goods.update"})
    public void saveListener(GoodsDTO goodsDTO, Channel channel, Message message) throws IOException {
        GoodsDoc goodsDoc = new GoodsDoc();
        BeanUtils.copyProperties(goodsDTO, goodsDoc);
        // 将商品数据写入搜索引擎
        goodsService.save(goodsDoc);

    }

    @RabbitListener(queues = {"pms.goods.del"})
    public void delListener(Channel channel, Message message) throws IOException {
        // 如果消息体是 Long 类型，可以在这里做转换
        Long spuId = Long.parseLong(new String(message.getBody(), StandardCharsets.UTF_8));
        // 删除索引库的商品
        goodsService.deleteById(spuId);
    }
}
