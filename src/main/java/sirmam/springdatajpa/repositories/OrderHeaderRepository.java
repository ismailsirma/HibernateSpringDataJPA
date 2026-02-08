package sirmam.springdatajpa.repositories;

import org.springframework.data.repository.CrudRepository;
import sirmam.springdatajpa.domain.OrderHeader;

public interface OrderHeaderRepository extends CrudRepository<OrderHeader, Long> {


}
