package cn.sevenyuan.demo.rocketmq;

import cn.sevenyuan.demo.domain.Book;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author JingQ at 4/7/20
 */
@Service
public class RocketMqProducerTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessageTest() {
        Book book1 = new Book("Book1", 1, "John", BigDecimal.TEN);
        Book book2 = new Book("Book2", 2, "Tom", BigDecimal.ONE);
        Book book3 = new Book("Book3", 3, "Jacky", BigDecimal.TEN);
        Book book4 = new Book("Book4", 4, "Jerry", BigDecimal.TEN);
        rocketMQTemplate.convertAndSend("cart-item-add-topic", book1);
        rocketMQTemplate.convertAndSend("cart-item-update-topic", book2);
        rocketMQTemplate.convertAndSend("cart-item-remove-topic", book3);
        rocketMQTemplate.convertAndSend("cart-item-search-topic", book4);
    }

    public void addCustomBook(String topic, Integer id, String bookName, String author, BigDecimal money) {
        Book book = new Book(bookName, id, author, money);
        rocketMQTemplate.convertAndSend(topic, book);
    }
}
