package cn.sevenyuan.demo.rocketmq.consumer;

import cn.sevenyuan.demo.domain.Book;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author JingQ at 4/8/20
 */
@Service
@Slf4j
@RocketMQMessageListener(topic = "cart-item-add-topic", consumerGroup = "cart-add-group")
public class CartAddTopicConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        Book book = JSON.parseObject(s, Book.class);
        System.out.println(book);
    }
}
