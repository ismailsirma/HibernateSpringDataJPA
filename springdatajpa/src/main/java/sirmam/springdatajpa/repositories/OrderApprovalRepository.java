package sirmam.springdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sirmam.springdatajpa.domain.OrderApproval;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}
