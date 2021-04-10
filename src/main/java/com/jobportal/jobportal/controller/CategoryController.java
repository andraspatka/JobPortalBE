package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.dto.CategoryDto;
import com.jobportal.jobportal.exceptions.CategoryAlreadyExistsException;
import com.jobportal.jobportal.exceptions.CategoryNotAddedException;
import com.jobportal.jobportal.service.CategoryService;
import com.jobportal.openapi.api.CategoriesApi;
import com.jobportal.openapi.model.AuthenticationResponse;
import com.jobportal.openapi.model.CategoriesInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for category for a posting
 *
 * @since 03.04.2021
 */
@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CategoryController implements CategoriesApi {

    private static final String CATEGORY_ADDED_MESSAGE = "Category was successfully added";
    private static final String CATEGORY_NOT_ADDED_MESSAGE = "Category could not be added";

    private final CategoryService categoryService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<CategoriesInformation>> categoriesGet() {
        List<CategoriesInformation> categories = new ArrayList<>();
        categoryService.getCategories().forEach(category -> {
            CategoriesInformation categoryInformation = new CategoriesInformation();
            categoryInformation.setName(category.getName());
            categories.add(categoryInformation);

        });
        return ResponseEntity.ok(categories);
    }

    /**
     * @param categoriesInformation (optional)
     * @return ResponseEntity<AuthenticationResponse>
     */
    @Override
    public ResponseEntity<AuthenticationResponse> categoriesPost(@Valid CategoriesInformation categoriesInformation) {
        final CategoryDto categoryDto = CategoryDto
                .builder()
                .name(categoriesInformation.getName())
                .build();
        try {
            AuthenticationResponse response = new AuthenticationResponse();
            categoryService.addCategory(categoryDto);
            response.setBody(CATEGORY_ADDED_MESSAGE);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (CategoryAlreadyExistsException | CategoryNotAddedException e) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(CATEGORY_NOT_ADDED_MESSAGE);
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(response);
        }
    }
}
