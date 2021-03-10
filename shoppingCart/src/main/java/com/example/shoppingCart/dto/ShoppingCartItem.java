package com.example.shoppingCart.dto;

import com.example.shoppingCart.dto.response.ShoppingCartResponse;

import java.util.List;

public class ShoppingCartItem {
    public List<ShoppingCartResponse> shoppingCartResponses;

    public List<ShoppingCartResponse> getShoppingCarts() {
        return shoppingCartResponses;
    }

    public void setShoppingCarts(List<ShoppingCartResponse> shoppingCartResponses) {
        this.shoppingCartResponses = shoppingCartResponses;
    }
}
