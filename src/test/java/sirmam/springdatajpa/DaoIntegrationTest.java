package sirmam.springdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import sirmam.springdatajpa.dao.AuthorDao;
import sirmam.springdatajpa.dao.AuthorDaoImpl;
import sirmam.springdatajpa.dao.BookDaoImpl;
import sirmam.springdatajpa.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"sirmam.springdatajpa"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({BookDaoImpl.class, AuthorDaoImpl.class})
public class DaoIntegrationTest {
    @Autowired
    AuthorDao authorDao;


    @Test
    @Transactional
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Thompson");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    @Transactional
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Thompson");
        Author updated = authorDao.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("Thompson");
    }

    @Test
    @Transactional
    void testAuthorByLastNameLike() {

        Author author = new Author();
        author.setFirstName("johnny1");
        author.setLastName("Wall");
        authorDao.saveNewAuthor(author);

        List<Author> authors = authorDao.findAllAuthorsByLastName("Wall", Pageable.unpaged());

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void testGetAuthorByName() {
        Author authorNew = new Author();
        authorNew.setFirstName("Craig");
        authorNew.setLastName("Walls");
        authorDao.saveNewAuthor(authorNew);

        Author author = authorDao.findAuthorByName("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    @Transactional
    void testGetAuthor() {

        Author authorNew = new Author();
        authorNew.setFirstName("Craig2");
        authorNew.setLastName("Walls2");
        Author saved = authorDao.saveNewAuthor(authorNew);

        Author author = authorDao.getById(saved.getId());

        assertThat(author).isNotNull();

    }

    @Test
    @Transactional
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("johnny");
        author.setLastName("t2");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());


        Author deleted = authorDao.getById(saved.getId());
        assertThat(deleted).isNull();

        assertThat(authorDao.getById(saved.getId()));

    }
}
