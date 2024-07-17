package by.peachack.store.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must be not empty")
    @Size(max = 255, message = "Name must be less than 255 characters")
    @Column(name = "name")
    private String name;

    @Size(max = 255, message = "Description must be less than 255 characters")
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category must be not empty")
    private Category category;

    @NotNull(message = "Price must be not empty")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Column(name = "price")
    private BigDecimal price;

    @NotNull(message = "Stock quantity must be not empty")
    @Min(value = 0, message = "Stock quantity must be greater than or equal to 0")
    @Column(name = "stock_quantity")
    private Integer stockQuantity;}
