package sirmam.springdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sirmam.springdatajpa.domain.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByCustomerNameIgnoreCase(String customerName);
}