package cn.sevenyuan.demo.aop;

import cn.sevenyuan.demo.domain.Book;
import cn.sevenyuan.demo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JingQ at 2019-08-07
 */
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }

    public void deleteById(Integer id) {
        System.out.println("deleting book ... id is " + id);
        bookMapper.deleteBookById(id);
    }

    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    public int updateBook(Book book) {
        return bookMapper.updateBookById(book);
    }
}
