package by.peachack.store.service;

import by.peachack.store.domain.Category;
import by.peachack.store.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Category with id %d not found", id)));
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(String.format("Category with name %s not found", name)));
    }

    @Override
    public Boolean removeCategory(Long id) {
        boolean exists = categoryRepository.existsById(id);
        if (exists) {
            categoryRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }
}
