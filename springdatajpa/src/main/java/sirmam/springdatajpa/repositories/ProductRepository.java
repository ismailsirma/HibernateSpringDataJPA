package sirmam.springdatajpa.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import sirmam.springdatajpa.domain.Product;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(Long aLong);

    Optional<Product> findByDescription(String description);
}
