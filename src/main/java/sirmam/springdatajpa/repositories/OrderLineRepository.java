package sirmam.springdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sirmam.springdatajpa.domain.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
