package cn.sevenyuan.demo.mapper;

import cn.sevenyuan.demo.domain.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * book dao
 * @author JingQ at 2019-08-08
 */
@Mapper
public interface BookMapper {

    int addBook(Book book);

    int deleteBookById(Integer id);

    int updateBookById(Book book);

    Book getBookById(Integer id);

    List<Book> getAllBooks();
}
