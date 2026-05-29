package sirmam.springdatajpa.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sirmam.springdatajpa.domain.OrderHeader;
import sirmam.springdatajpa.repositories.OrderHeaderRepository;

import java.util.Optional;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Transactional
    // mark transactional to retrieve inner properties of categories to prevent lazy initialization exception
    @Override
    public void run(String... args) throws Exception {

        Optional<OrderHeader> orderHeader = orderHeaderRepository.findById(1L);

        orderHeader.ifPresent(header -> header.getOrderLines().forEach(ol -> {
            System.out.println(ol.getProduct().getDescription());

            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription());
            });
        }));

        System.out.println("I was called!");
    }
}
