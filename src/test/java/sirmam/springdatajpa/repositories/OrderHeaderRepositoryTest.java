package sirmam.springdatajpa.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sirmam.springdatajpa.domain.OrderHeader;
import sirmam.springdatajpa.domain.OrderLine;
import sirmam.springdatajpa.domain.Product;
import sirmam.springdatajpa.domain.ProductStatus;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        Product newProduct = new Product();
        newProduct.setProductStatus(ProductStatus.NEW);
        newProduct.setDescription("test product");
        product = productRepository.saveAndFlush(newProduct);
    }

    @Test
    void testSaveOrderWithLine() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New customer");

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);

        orderHeader.setOrderlines(Set.of(orderLine));
        // inversely assign the relation
        orderLine.setOrderHeader(orderHeader);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        // persist changes to db
        orderHeaderRepository.flush();

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderlines());
        assertEquals(savedOrder.getOrderlines().size(), 1);

        // fetch saved order info
        OrderHeader fetchedOrder = orderHeaderRepository.findById(savedOrder.getId()).orElse(null);
        assertNotNull(fetchedOrder);
        assertEquals(fetchedOrder.getOrderlines().size(), 1);
    }

    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New customer");
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        OrderHeader fetchedOrder = orderHeaderRepository.findById(savedOrder.getId()).orElse(null);

        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
        assertNotNull(fetchedOrder.getCreatedDate());
        assertNotNull(fetchedOrder.getUpdatedDate());
    }
}