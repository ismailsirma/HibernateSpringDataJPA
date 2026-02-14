package sirmam.springdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sirmam.springdatajpa.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {

}
