package com.example.shoppingCart.controller;

import com.example.shoppingCart.dto.request.ProductRequest;
import com.example.shoppingCart.dto.response.ProductResponse;
import com.example.shoppingCart.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    ProductService productService;

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return status(HttpStatus.OK).body(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return status(HttpStatus.OK).body(productService.getProduct(id));
    }

    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest){
        return status(HttpStatus.OK).body(productService.update(productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
