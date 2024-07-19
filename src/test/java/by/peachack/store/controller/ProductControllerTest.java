package by.peachack.store.controller;

import by.peachack.store.domain.Category;
import by.peachack.store.domain.Product;
import by.peachack.store.dto.ProductDTO;
import by.peachack.store.service.category.CategoryService;
import by.peachack.store.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCreateProduct_thenReturnCreatedProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Laptop");
        productDTO.setDescription("A high-end laptop");
        productDTO.setCategoryId(1L);
        productDTO.setPrice(new BigDecimal("1500.00"));
        productDTO.setStockQuantity(10);

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("A high-end laptop");
        product.setCategory(new Category());
        product.setPrice(new BigDecimal("1500.00"));
        product.setStockQuantity(10);

        when(categoryService.findCategory(anyLong())).thenReturn(new Category());
        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Laptop"));

        verify(categoryService, times(1)).findCategory(anyLong());
        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void whenGetAllProducts_thenReturnListOfProducts() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(productService.findProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(productService, times(1)).findProducts();
    }

    @Test
    void whenGetProductById_thenReturnProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        when(productService.findProduct(anyLong())).thenReturn(product);

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Laptop"));

        verify(productService, times(1)).findProduct(anyLong());
    }

    @Test
    void whenGetProductByIdAndNotFound_thenReturnNotFound() throws Exception {
        when(productService.findProduct(anyLong())).thenThrow(new NoSuchElementException("Product not found"));

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).findProduct(anyLong());
    }

    @Test
    void whenDeleteProduct_thenReturnNoContent() throws Exception {
        when(productService.removeProduct(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/products/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).removeProduct(anyLong());
    }

    @Test
    void whenDeleteProductAndNotFound_thenReturnNotFound() throws Exception {
        when(productService.removeProduct(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/api/products/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).removeProduct(anyLong());
    }

    @Test
    void whenUpdateProduct_thenReturnUpdatedProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Laptop");
        productDTO.setDescription("A high-end laptop");
        productDTO.setCategoryId(1L);
        productDTO.setPrice(new BigDecimal("1500.00"));
        productDTO.setStockQuantity(10);

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("A high-end laptop");
        product.setCategory(new Category());
        product.setPrice(new BigDecimal("1500.00"));
        product.setStockQuantity(10);

        when(categoryService.findCategory(anyLong())).thenReturn(new Category());
        when(productService.updateProduct(any(Product.class), anyLong())).thenReturn(product);

        mockMvc.perform(put("/api/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Laptop"));

        verify(categoryService, times(1)).findCategory(anyLong());
        verify(productService, times(1)).updateProduct(any(Product.class), anyLong());
    }

    @Test
    void whenUpdateProductAndNotFound_thenReturnNotFound() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Laptop");
        productDTO.setDescription("A high-end laptop");
        productDTO.setCategoryId(1L);
        productDTO.setPrice(new BigDecimal("1500.00"));
        productDTO.setStockQuantity(10);

        when(categoryService.findCategory(anyLong())).thenReturn(new Category());
        when(productService.updateProduct(any(Product.class), anyLong())).thenThrow(new NoSuchElementException("Product not found"));

        mockMvc.perform(put("/api/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isNotFound());

        verify(categoryService, times(1)).findCategory(anyLong());
        verify(productService, times(1)).updateProduct(any(Product.class), anyLong());
    }
}
