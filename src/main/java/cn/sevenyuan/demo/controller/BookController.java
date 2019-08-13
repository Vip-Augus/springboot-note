package cn.sevenyuan.demo.controller;

import cn.sevenyuan.demo.aop.BookService;
import cn.sevenyuan.demo.domain.Book;
import com.google.common.collect.Lists;
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
public class BookController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private BookService bookService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/bookView")
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

    @PostMapping("/save")
    public String saveName(@RequestParam("name") String name, HttpSession session) {
        session.setAttribute("name", name);
        return name;
    }

    @GetMapping("getSessionName")
    public String getSessionName(HttpSession session) {
        return port + ":" + session.getAttribute("name");
    }
}
