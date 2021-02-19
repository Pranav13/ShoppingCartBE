package com.example.shoppingCart.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShoppingCartDto {
    public Date dateCreated;
    public Long id;
    public List<Item> items;
}
