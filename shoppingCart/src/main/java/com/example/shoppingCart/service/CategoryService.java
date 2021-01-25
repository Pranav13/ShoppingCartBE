package com.example.shoppingCart.service;

import com.example.shoppingCart.model.Category;
import com.example.shoppingCart.dto.CategoryResponse;
import com.example.shoppingCart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {

    @Autowired
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
