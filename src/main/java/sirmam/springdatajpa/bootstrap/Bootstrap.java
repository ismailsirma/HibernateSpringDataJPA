package sirmam.springdatajpa.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sirmam.springdatajpa.domain.Customer;
import sirmam.springdatajpa.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner {


    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        // Transactional Proxy mode - external method calls works fine
        bootstrapOrderService.readOrderData();

        Customer customer = new Customer();
        customer.setCustomerName("Testing Version");
        Customer savedCustomer = customerRepository.save(customer);

        System.out.println("Version is: " + savedCustomer.getVersion());

        savedCustomer.setCustomerName("Testing Version 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer);

        System.out.println("Version is: " + savedCustomer2.getVersion());

        savedCustomer2.setCustomerName("Testing Version 3");
        Customer savedCustomer3 = customerRepository.save(savedCustomer2);

        System.out.println("Version is: " + savedCustomer3.getVersion());
        customerRepository.deleteById(savedCustomer3.getId());

        System.out.println("I was called!");
    }
}
