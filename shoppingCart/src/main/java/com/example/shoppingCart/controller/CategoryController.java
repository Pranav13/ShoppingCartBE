package com.example.shoppingCart.controller;

import com.example.shoppingCart.dto.response.CategoryResponse;
import com.example.shoppingCart.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        return status(HttpStatus.OK).body(categoryService.getAllCategory());
    }
}
