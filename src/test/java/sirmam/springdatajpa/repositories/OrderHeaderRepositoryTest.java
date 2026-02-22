package sirmam.springdatajpa.repositories;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sirmam.springdatajpa.domain.*;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderApprovalRepository orderApprovalRepository;

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

        orderHeader.addOrderLine(orderLine);

        OrderApproval approval = new OrderApproval();
        approval.setApprovedBy("me");
        OrderApproval savedApproval = orderApprovalRepository.save(approval);
        orderHeader.setOrderApproval(savedApproval);

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

    @Test
    void testDeleteCascade() {

        OrderHeader orderHeader = new OrderHeader();

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(3);
        orderLine.setProduct(product);

        orderHeader.addOrderLine(orderLine);
        OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);

        System.out.println("order saved and flushed");

        orderHeaderRepository.deleteById(savedOrder.getId());
        orderHeaderRepository.flush();

        assertThrows(EntityNotFoundException.class, () -> {
            OrderHeader fetched = orderHeaderRepository.getById(savedOrder.getId());
            assertNull(fetched);
        });
    }
}