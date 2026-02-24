package sirmam.springdatajpa.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sirmam.springdatajpa.domain.Category;
import sirmam.springdatajpa.domain.Product;
import sirmam.springdatajpa.domain.ProductStatus;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void testGetCategory() {
        Product newProduct = new Product();
        newProduct.setDescription("PRODUCT1");
        newProduct.setProductStatus(ProductStatus.NEW);

        Category category = new Category();
        category.setDescription("CATEGORY1");
        Category category2 = new Category();
        category2.setDescription("CATEGORY2");

        categoryRepository.saveAll(List.of(category, category2));

        newProduct.setCategories(Set.of(category, category2));

        productRepository.save(newProduct);

        Product product = productRepository.findByDescription("PRODUCT1").orElse(null);

        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productRepository.save(product);

        Product fetchedProduct = productRepository.getById(savedProduct.getId());

        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.getDescription());
        assertNotNull(fetchedProduct.getCreatedDate());
        assertNotNull(fetchedProduct.getUpdatedDate());
    }
}
