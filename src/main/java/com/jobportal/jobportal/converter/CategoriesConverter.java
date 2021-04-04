package com.jobportal.jobportal.converter;


import com.jobportal.jobportal.dto.CategoryDto;
import com.jobportal.jobportal.model.Category;
import lombok.experimental.UtilityClass;

/**
 * Class which converts a {@link CategoryDto} to a {@link Category} and vice versa
 */
@UtilityClass
public class CategoriesConverter {


    public static Category convertCategoryDtoToEntity(CategoryDto categoryDto){
        return Category.builder()
                .name(categoryDto.getName())
                .build();
    }

    public static CategoryDto convertCategoryEntityToDto(Category category){
        return CategoryDto.builder()
                .name(category.getName())
                .build();
    }
}
