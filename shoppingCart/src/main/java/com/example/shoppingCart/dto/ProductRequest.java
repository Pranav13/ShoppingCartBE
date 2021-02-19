package com.example.shoppingCart.dto;

import lombok.Data;

@Data
public class ProductRequest {
    public String id;
    public String title;
    public Integer price;
    public String category;
    public String imageurl;

}
