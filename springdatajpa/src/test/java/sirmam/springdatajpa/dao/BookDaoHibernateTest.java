package sirmam.springdatajpa.dao;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import sirmam.springdatajpa.domain.Author;
import sirmam.springdatajpa.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"sirmam.springdatajpa.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoHibernateTest {

    @Autowired
    EntityManagerFactory emf;

    BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookDao = new BookDaoHibernate(emf);
    }

    @Test
    void findAllBooksSortByTitle() {
        List<Book> books = bookDao.findAllBooksSortByTitle(PageRequest.of(0, 10,
                Sort.by(Sort.Order.desc("title"))));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void findAllBooks() {
        Book bookNew = new Book();
        bookNew.setIsbn("2222");
        bookNew.setPublisher("Self");
        bookNew.setTitle("my book4");
        bookDao.saveNewBook(bookNew);

        Book bookNew2 = new Book();
        bookNew2.setIsbn("3333");
        bookNew2.setPublisher("Self");
        bookNew2.setTitle("my book5");
        bookDao.saveNewBook(bookNew2);

        List<Book> books = bookDao.findAllBooks(PageRequest.of(0, 10));

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(1);
    }

    @Test
    void testFindAllBooks() {
    }

    @Test
    @Transactional
    void getById() {
        Book bookNew = new Book();
        bookNew.setIsbn("1234567");
        bookNew.setPublisher("Self");
        bookNew.setTitle("my book3");
        Book saved = bookDao.saveNewBook(bookNew);

        Book book = bookDao.getById(saved.getId());
        assertThat(book.getId()).isNotNull();
    }

    @Test
    void findBookByTitle() {
        Book book = bookDao.findBookByTitle("MY BOOK");
        assertThat(book).isNotNull();
    }

    @Test
    @Transactional
    void saveNewBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("MY BOOK");

        Author author = new Author();
        author.setId(3L);
        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    @Transactional
    void updateBook() {
        Book book = new Book();
        book.setIsbn("123456");
        book.setPublisher("Self");
        book.setTitle("my book");

        Author author = new Author();
        author.setId(1L);

        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("New Book");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }

    @Test
    void deleteBookById() {
        Book book = new Book();
        book.setIsbn("12345");
        book.setPublisher("Self");
        book.setTitle("my book");
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        Book deleted = bookDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }
}