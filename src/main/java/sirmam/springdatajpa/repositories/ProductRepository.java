package sirmam.springdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sirmam.springdatajpa.domain.Product;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByDescription(String description);
}
