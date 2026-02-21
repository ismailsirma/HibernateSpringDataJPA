package sirmam.springdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sirmam.springdatajpa.domain.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByDescription(String description);
}
