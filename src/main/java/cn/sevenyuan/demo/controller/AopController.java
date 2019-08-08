package cn.sevenyuan.demo.controller;

import cn.sevenyuan.demo.aop.BookService;
import cn.sevenyuan.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JingQ at 2019-08-07
 */
@RequestMapping(value = "bookAop")
@RestController
public class AopController {

    @Autowired
    private BookService bookService;

    @GetMapping("getById")
    public String getById(@RequestParam("id") Integer id) {
        Book book = bookService.getBookById(id);
        return book == null ? null : book.getName();
    }

    @GetMapping("deleteById")
    public void delete(@RequestParam("id") Integer id) {
        bookService.deleteById(id);
    }
}
