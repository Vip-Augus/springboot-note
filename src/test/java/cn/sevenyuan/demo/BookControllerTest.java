package cn.sevenyuan.demo;

import cn.sevenyuan.demo.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * web 层单元测试
 * 使用到了 redis 和 mysql，记得先启动
 * @author JingQ at 2019-08-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test1() {
        ResponseEntity<Book> book = restTemplate.getForEntity("/book/getById?id=1", Book.class);
        System.out.println(book.getBody());
    }
}
