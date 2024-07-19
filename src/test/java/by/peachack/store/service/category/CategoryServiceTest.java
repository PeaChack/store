package by.peachack.store.service.category;

import by.peachack.store.domain.Category;
import by.peachack.store.repository.CategoryRepository;
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

class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DefaultCategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveCategory_thenReturnSavedCategory() {
        Category category = new Category();
        when(categoryRepository.save(category)).thenReturn(category);

        Category savedCategory = categoryService.saveCategory(category);

        assertNotNull(savedCategory);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void whenFindCategories_thenReturnListOfCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> foundCategories = categoryService.findCategories();

        assertEquals(2, foundCategories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void whenFindCategoryById_thenReturnCategory() {
        Category category = new Category();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.findCategory(1L);

        assertNotNull(foundCategory);
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void whenFindCategoryByIdAndNotFound_thenThrowNoSuchElementException() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> categoryService.findCategory(1L));
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void whenFindCategoryByName_thenReturnCategory() {
        Category category = new Category();
        when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.findCategoryByName("Electronics");

        assertNotNull(foundCategory);
        verify(categoryRepository, times(1)).findByName("Electronics");
    }

    @Test
    void whenFindCategoryByNameAndNotFound_thenThrowNoSuchElementException() {
        when(categoryRepository.findByName("Electronics")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> categoryService.findCategoryByName("Electronics"));
        verify(categoryRepository, times(1)).findByName("Electronics");
    }

    @Test
    void whenRemoveCategory_thenReturnTrue() {
        when(categoryRepository.existsById(1L)).thenReturn(true);
        doNothing().when(categoryRepository).deleteById(1L);

        boolean result = categoryService.removeCategory(1L);

        assertTrue(result);
        verify(categoryRepository, times(1)).existsById(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void whenRemoveCategoryAndNotFound_thenReturnFalse() {
        when(categoryRepository.existsById(1L)).thenReturn(false);

        boolean result = categoryService.removeCategory(1L);

        assertFalse(result);
        verify(categoryRepository, times(1)).existsById(1L);
        verify(categoryRepository, never()).deleteById(1L);
    }

    @Test
    void whenUpdateCategory_thenReturnUpdatedCategory() {
        Category category = new Category();
        when(categoryRepository.existsById(1L)).thenReturn(true);
        when(categoryRepository.save(category)).thenReturn(category);

        Category updatedCategory = categoryService.updateCategory(category, 1L);

        assertNotNull(updatedCategory);
        verify(categoryRepository, times(1)).existsById(1L);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void whenUpdateCategoryAndNotFound_thenThrowNoSuchElementException() {
        Category category = new Category();
        when(categoryRepository.existsById(1L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> categoryService.updateCategory(category, 1L));
        verify(categoryRepository, times(1)).existsById(1L);
        verify(categoryRepository, never()).save(category);
    }}