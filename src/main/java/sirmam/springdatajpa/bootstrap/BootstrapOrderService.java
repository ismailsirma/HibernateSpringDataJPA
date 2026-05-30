package sirmam.springdatajpa.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sirmam.springdatajpa.domain.OrderHeader;
import sirmam.springdatajpa.repositories.OrderHeaderRepository;

import java.util.Optional;

@Service
public class BootstrapOrderService {
    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Transactional
    // mark transactional to retrieve inner properties of categories to prevent lazy initialization exception
    public void readOrderData() {
        Optional<OrderHeader> orderHeader = orderHeaderRepository.findById(1L);

        orderHeader.ifPresent(header -> header.getOrderLines().forEach(ol -> {
            System.out.println(ol.getProduct().getDescription());

            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription());
            });
        }));

    }
}
