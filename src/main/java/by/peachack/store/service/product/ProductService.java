package by.peachack.store.service.product;

import by.peachack.store.domain.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> findProducts();
    Product findProduct(Long id);
    Product findProductByName(String name);
    Boolean removeProduct(Long id);
    Product updateProduct(Product product, Long id);
}
