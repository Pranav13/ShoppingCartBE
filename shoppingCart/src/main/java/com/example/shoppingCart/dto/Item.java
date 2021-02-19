package com.example.shoppingCart.dto;

import lombok.Data;

@Data
public class Item{
    public String id;
    public String title;
    public double price;
    public String category;
    public String imageurl;
    public int quantity;
}