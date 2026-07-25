package sirmam.springdatajpa.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sirmam.springdatajpa.domain.Customer;
import sirmam.springdatajpa.domain.Product;
import sirmam.springdatajpa.domain.ProductStatus;
import sirmam.springdatajpa.domain.joinedtable.ElectricGuitar;
import sirmam.springdatajpa.repositories.CustomerRepository;
import sirmam.springdatajpa.repositories.ElectricGuitarRepository;
import sirmam.springdatajpa.services.ProductService;

@Component
public class Bootstrap implements CommandLineRunner {


    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ElectricGuitarRepository electricGuitarRepository;

    private void updateProduct(){
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productService.saveProduct(product);

        Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

        System.out.println(savedProduct2.getQuantityOnHand());
    }
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        updateProduct();

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

        ElectricGuitar eg = new ElectricGuitar();
        eg.setNumberOfStrings(6);
        eg.setNumberOfPickups(2);
        electricGuitarRepository.save(eg);

        System.out.println("I was called!");
    }
}
