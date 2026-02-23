package sirmam.springdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sirmam.springdatajpa.domain.Product;
import sirmam.springdatajpa.repositories.OrderHeaderRepository;
import sirmam.springdatajpa.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLoadTest {
    final String PRODUCT_01 = "Product 1";
    final String PRODUCT_02 = "Product 2";
    final String PRODUCT_03 = "Product 3";

    final String TEST_CUSTOMER = "TEST CUSTOMER";

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void testDataLoader() {
        List<Product> products = new ArrayList<>();
    }

}
