package by.peachack.store.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must be not empty")
    @Size(max = 255, message = "Name must be less than 255 characters")
    @Column(name = "name")
    private String name;

    @Size(max = 255, message = "Description must be less than 255 characters")
    @Column(name = "description")
    private String description;}
