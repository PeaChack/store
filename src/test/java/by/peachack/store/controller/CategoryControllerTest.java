package by.peachack.store.controller;

import by.peachack.store.domain.Category;
import by.peachack.store.service.category.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCreateCategory_thenReturnCreatedCategory() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        when(categoryService.saveCategory(any(Category.class))).thenReturn(category);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Electronics"));

        verify(categoryService, times(1)).saveCategory(any(Category.class));
    }

    @Test
    void whenGetAllCategories_thenReturnListOfCategories() throws Exception {
        List<Category> categories = Arrays.asList(new Category(), new Category());

        when(categoryService.findCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(categoryService, times(1)).findCategories();
    }

    @Test
    void whenGetCategoryById_thenReturnCategory() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        when(categoryService.findCategory(anyLong())).thenReturn(category);

        mockMvc.perform(get("/api/categories/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Electronics"));

        verify(categoryService, times(1)).findCategory(anyLong());
    }

    @Test
    void whenGetCategoryByIdAndNotFound_thenReturnNotFound() throws Exception {
        when(categoryService.findCategory(anyLong())).thenThrow(new NoSuchElementException("Category not found"));

        mockMvc.perform(get("/api/categories/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(categoryService, times(1)).findCategory(anyLong());
    }

    @Test
    void whenDeleteCategory_thenReturnNoContent() throws Exception {
        when(categoryService.removeCategory(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/categories/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(categoryService, times(1)).removeCategory(anyLong());
    }

    @Test
    void whenDeleteCategoryAndNotFound_thenReturnNotFound() throws Exception {
        when(categoryService.removeCategory(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/api/categories/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(categoryService, times(1)).removeCategory(anyLong());
    }

    @Test
    void whenUpdateCategory_thenReturnUpdatedCategory() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        when(categoryService.updateCategory(any(Category.class), anyLong())).thenReturn(category);

        mockMvc.perform(put("/api/categories/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Electronics"));

        verify(categoryService, times(1)).updateCategory(any(Category.class), anyLong());
    }

    @Test
    void whenUpdateCategoryAndNotFound_thenReturnNotFound() throws Exception {
        when(categoryService.updateCategory(any(Category.class), anyLong())).thenThrow(new NoSuchElementException("Category not found"));

        mockMvc.perform(put("/api/categories/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Category())))
                .andExpect(status().isNotFound());

        verify(categoryService, times(1)).updateCategory(any(Category.class), anyLong());
    }
}