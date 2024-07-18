package by.peachack.store.service.category;

import by.peachack.store.domain.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> findCategories();
    Category findCategory(Long id);
    Category findCategoryByName(String name);
    Boolean removeCategory(Long id);
    Category updateCategory(Category category, Long id);
}
