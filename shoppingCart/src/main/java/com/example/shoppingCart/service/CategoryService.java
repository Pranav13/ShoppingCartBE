package com.example.shoppingCart.service;

import com.example.shoppingCart.model.Category;
import com.example.shoppingCart.dto.response.CategoryResponse;
import com.example.shoppingCart.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategory() {
        return  categoryRepository.findAll()
                .stream()
                .map(this::getCategoryResponse)
                .collect(toList());
    }

    private CategoryResponse getCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId().toString());
        categoryResponse.setCategoryname(category.getCategoryName());
        return categoryResponse;
    }
}
