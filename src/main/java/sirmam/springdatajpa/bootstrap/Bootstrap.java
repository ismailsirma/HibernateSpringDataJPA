package sirmam.springdatajpa.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {


    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Override
    public void run(String... args) throws Exception {
        // Transactional Proxy mode - external method calls works fine
        bootstrapOrderService.readOrderData();
        System.out.println("I was called!");
    }
}
