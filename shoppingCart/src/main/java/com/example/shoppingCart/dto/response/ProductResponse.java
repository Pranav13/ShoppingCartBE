package com.example.shoppingCart.dto.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String title;
    private double price;
    private String imageurl;
    private Long category;
}
