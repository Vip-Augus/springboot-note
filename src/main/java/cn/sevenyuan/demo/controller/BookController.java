package cn.sevenyuan.demo.controller;

import cn.sevenyuan.demo.aop.BookService;
import cn.sevenyuan.demo.domain.Book;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author JingQ at 2019-08-04
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

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

}
