package cn.sevenyuan.demo.controller;

import cn.sevenyuan.demo.aop.BookService;
import cn.sevenyuan.demo.aop.lock.RedisLockAnnotation;
import cn.sevenyuan.demo.aop.lock.RedisLockTypeEnum;
import cn.sevenyuan.demo.domain.Book;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author JingQ at 2019-08-04
 */
@RestController
@RequestMapping("/book")
@Api(tags = "书籍接口")
@Slf4j
public class BookController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private BookService bookService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RocketMqProducerTest mqProducerTest;

    @GetMapping("/bookView")
    @ApiOperation(value = "查看图书清单", notes = "mock 的数据", tags = "1.0.0")
    public ModelAndView books() {
        ModelAndView mv = new ModelAndView();
        List<Book> bookList = Lists.newArrayList();
        Book book1 = new Book("Go 语言基础", 1, "none name", new BigDecimal(20));
        book1.setPublishDate(new Date());
        bookList.add(book1);
        bookList.add(new Book("Go 语言实战", 2, "none name", new BigDecimal(30)));
        bookList.add(new Book("Go 语言并发之道", 3, "none name", new BigDecimal(40)));
        mv.addObject("books", bookList);
        mv.setViewName("books");
        return mv;
    }

    @GetMapping("/getById")
    @ApiOperation(value = "根据 id 查询书籍", tags = "1.0.0", notes = "真实数据")
    public Book getById(@RequestParam("id") Integer id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            book.setPublishDate(new Date());
        }
        return book;
    }

    @GetMapping("/testRedis")
    public Book getForRedis() {
        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set("name", "Go 语言实战");
        String name = ops1.get("name");
        System.out.println(name);
        ValueOperations ops2 = redisTemplate.opsForValue();
        Book book = (Book) ops2.get("b1");
        if (book == null) {
            book = new Book("Go 语言实战", 2, "none name", BigDecimal.ONE);
            ops2.set("b1", book);
        }
        return book;
    }

    @GetMapping("/testRedisLock")
    @RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE, lockTime = 3)
    public Book testRedisLock(@RequestParam("userId") Long userId) {
        try {
            log.info("睡眠执行前");
            Thread.sleep(10000);
            log.info("睡眠执行后");
        } catch (Exception e) {
            // log error
            log.info("has some error", e);
        }
        return null;
    }

    @GetMapping("/save")
    public String saveName(@RequestParam("name") String name, HttpSession session) {
        session.setAttribute("name", name);
        return name;
    }

    @GetMapping("getSessionName")
    public String getSessionName(HttpSession session) {
        return port + ":" + session.getAttribute("name");
    }

    @GetMapping("/testMessage")
    public String testMessage() {
        mqProducerTest.sendMessageTest();
        return "Hello World";
    }
}
