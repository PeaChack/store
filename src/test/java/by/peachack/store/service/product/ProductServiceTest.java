package by.peachack.store.service.product;

import by.peachack.store.domain.Product;
import by.peachack.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DefaultProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveProduct_thenReturnSavedProduct() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertNotNull(savedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void whenFindProducts_thenReturnListOfProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        List<Product> foundProducts = productService.findProducts();

        assertEquals(2, foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void whenFindProductById_thenReturnProduct() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findProduct(1L);

        assertNotNull(foundProduct);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void whenFindProductByIdAndNotFound_thenThrowNoSuchElementException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productService.findProduct(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void whenFindProductByName_thenReturnProduct() {
        Product product = new Product();
        when(productRepository.findByName("Laptop")).thenReturn(Optional.of(product));

        Product foundProduct = productService.findProductByName("Laptop");

        assertNotNull(foundProduct);
        verify(productRepository, times(1)).findByName("Laptop");
    }

    @Test
    void whenFindProductByNameAndNotFound_thenThrowNoSuchElementException() {
        when(productRepository.findByName("Laptop")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productService.findProductByName("Laptop"));
        verify(productRepository, times(1)).findByName("Laptop");
    }

    @Test
    void whenRemoveProduct_thenReturnTrue() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);

        boolean result = productService.removeProduct(1L);

        assertTrue(result);
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void whenRemoveProductAndNotFound_thenReturnFalse() {
        when(productRepository.existsById(1L)).thenReturn(false);

        boolean result = productService.removeProduct(1L);

        assertFalse(result);
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, never()).deleteById(1L);
    }

    @Test
    void whenUpdateProduct_thenReturnUpdatedProduct() {
        Product product = new Product();
        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product, 1L);

        assertNotNull(updatedProduct);
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void whenUpdateProductAndNotFound_thenThrowNoSuchElementException() {
        Product product = new Product();
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> productService.updateProduct(product, 1L));
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, never()).save(product);
    }
}