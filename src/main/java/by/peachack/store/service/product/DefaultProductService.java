package by.peachack.store.service.product;

import by.peachack.store.domain.Product;
import by.peachack.store.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Product with id %d not found", id)));
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(String.format("Product with name %s not found", name)));
    }

    @Override
    public Boolean removeProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (exists) {
            productRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        boolean exists = productRepository.existsById(id);
        if (exists) {
            product.setId(id);
            return productRepository.save(product);
        } else
            throw new NoSuchElementException(String.format("Product with id %d not found", id));
    }
}
