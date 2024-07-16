package by.peachack.store.service;

import by.peachack.store.domain.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product category);
    List<Product> findProducts();
    Product findProduct(Long id);
    Product findProductByName(String name);
    Boolean removeProduct(Product category);
    Product updateProduct(Product category);
}
