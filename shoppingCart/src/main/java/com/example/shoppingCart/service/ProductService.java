package com.example.shoppingCart.service;

import com.example.shoppingCart.dto.ProductRequest;
import com.example.shoppingCart.dto.ProductResponse;
import com.example.shoppingCart.exception.CategoryNotFoundException;
import com.example.shoppingCart.exception.ProductNotFoundException;
import com.example.shoppingCart.model.Category;
import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.repository.CategoryRepository;
import com.example.shoppingCart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public void save(ProductRequest productRequest){
        Product product = new Product();
        Category category = categoryRepository.findById(Long.parseLong(productRequest.getCategory()))
                .orElseThrow(() -> new CategoryNotFoundException(product.getCategory().getId().toString()));
        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category);
        product.setImageurl(productRequest.getImageurl());
        productRepository.save(product);
    }

    public List<ProductResponse> getAllProduct() {
       return  productRepository.findAll()
                .stream()
                .map(this::getProductResponse)
                .collect(toList());
    }

    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
        return getProductResponse(product);
    }

    private ProductResponse getProductResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setTitle(product.getTitle());
        productResponse.setImageurl(product.getImageurl());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategory(product.getCategory().getId());
        return productResponse;
    }

    public ProductResponse update(ProductRequest productRequest) {
        Product product = new Product();
        Category category = categoryRepository.findById(Long.parseLong(productRequest.getCategory()))
                .orElseThrow(() -> new CategoryNotFoundException(product.getCategory().getId().toString()));
        product.setId(Long.parseLong(productRequest.getId()));
        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category);
        product.setImageurl(productRequest.getImageurl());
        if(productRepository.findById(product.getId()).isPresent()){
            return getProductResponse(productRepository.save(product));
        }else{
            throw new ProductNotFoundException("No Book found with book id"+product.getId());
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
