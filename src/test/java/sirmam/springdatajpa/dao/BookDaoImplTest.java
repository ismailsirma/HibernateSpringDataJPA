package sirmam.springdatajpa.dao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import sirmam.springdatajpa.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(BookDaoImpl.class)
class BookDaoImplTest {

    private static Logger LOGGER = LoggerFactory.getLogger(BookDaoImplTest.class);
    @Autowired
    BookDao bookDao;

    @Test
    @Transactional
    void findAllBooksPage1_SortByTitle() {

        Book book = new Book();
        book.setIsbn("123");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);
        bookDao.saveNewBook(book);

        List<Book> books = bookDao.findAllBooksSortByTitle(PageRequest.of(0, 10,
                Sort.by(Sort.Order.desc("title"))));

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void findAllBooksPage1_pageable() {

        Book book = new Book();
        book.setIsbn("123");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);
        bookDao.saveNewBook(book);

        Book book2 = new Book();
        book2.setIsbn("1222");
        book2.setPublisher("Self");
        book2.setTitle("my book2");
        book2.setAuthorId(1L);
        bookDao.saveNewBook(book2);

        List<Book> books = bookDao.findAllBooks(PageRequest.of(0, 10));

        LOGGER.info(".....");
        LOGGER.info(books.toString());

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void findAllBooksPage2_pageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(1, 10));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void findAllBooksPage10_pageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(10, 10));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    @Transactional
    void testFindAllBooks() {
        Book book = new Book();
        book.setIsbn("123");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);
        bookDao.saveNewBook(book);

        List<Book> books = bookDao.findAllBooks();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void getById() {
        Book book = bookDao.getById(1L);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    @Transactional
    void findBookByTitle() {
        Book book = new Book();
        book.setIsbn("123");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);
        bookDao.saveNewBook(book);

        Book bookFound = bookDao.findBookByTitle("my book");

        assertThat(bookFound).isNotNull();
    }

    @Test
    @Transactional
    void saveNewBook() {
        Book book = new Book();
        book.setIsbn("123");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);

        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    @Transactional
    void updateBook() {
        Book book = new Book();
        book.setIsbn("12345678");
        book.setPublisher("Self");
        book.setTitle("my book2");
        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("New Book");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }

    @Test
    @Transactional
    void deleteBookById() {

        Book book = new Book();
        book.setIsbn("4444");
        book.setPublisher("Self");
        book.setTitle("my book7");
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            bookDao.getById(saved.getId());
        });
    }
}