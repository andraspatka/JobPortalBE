package com.jobportal.jobportal.service;

import com.jobportal.jobportal.converter.CategoriesConverter;
import com.jobportal.jobportal.dto.CategoryDto;
import com.jobportal.jobportal.exceptions.CategoryAlreadyExistsException;
import com.jobportal.jobportal.exceptions.CategoryNotExistingException;
import com.jobportal.jobportal.model.Category;
import com.jobportal.jobportal.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service which contains functionalities for Category
 *
 * @since 03.04.2021
 */
@Service
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategory(@NonNull CategoryDto categoryDto) {
        Category category = CategoriesConverter.convertCategoryDtoToEntity(categoryDto);

        if (categoryAlreadyExists(category.getName()))
            throw new CategoryAlreadyExistsException("Category already exists. Try to add a different category");
        categoryRepository.save(category);
    }

    public void deleteCategory(@NonNull Long id) {
        if (!idExists(id))
            throw new CategoryNotExistingException("Category does not exists. Delete failed.");
        categoryRepository.deleteById(id);
    }

    /**
     * Returns a lists containing all the posting's
     * categories.
     */
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoriesConverter::convertCategoryEntityToDto)
                .collect(Collectors.toList());
    }

    private boolean categoryAlreadyExists(String name) {
        return categoryRepository.findCategoriesByName(name).isPresent();
    }

    private boolean idExists(Long id) {
        return categoryRepository.findById(id).isPresent();
    }
}
