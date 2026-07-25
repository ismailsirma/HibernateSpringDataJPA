package sirmam.springdatajpa.services;

import sirmam.springdatajpa.domain.Product;

public interface ProductService {
    Product saveProduct(Product product);

    Product updateQOH(Long id, Integer quantityOnHand);
}
