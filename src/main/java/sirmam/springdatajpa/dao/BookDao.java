package sirmam.springdatajpa.dao;

import org.springframework.data.domain.Pageable;
import sirmam.springdatajpa.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAllBooksSortByTitle(Pageable pageable);
    List<Book> findAllBooks(Pageable pageable);
    List<Book> findAllBooks();
    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

}
