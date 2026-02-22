package sirmam.springdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sirmam.springdatajpa.domain.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {


}
