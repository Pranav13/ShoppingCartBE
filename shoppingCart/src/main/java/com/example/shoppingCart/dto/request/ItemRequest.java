package com.example.shoppingCart.dto.request;

import lombok.Data;

@Data
public class ItemRequest {
    public String id;
    public String title;
    public double price;
    public String category;
    public String imageurl;
    public int quantity;
}