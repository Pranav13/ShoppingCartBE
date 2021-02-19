package com.example.shoppingCart.dto;

import java.util.List;

public class ShoppingCartItem {
    public List<ShoppingCartDto> shoppingCartDtos;

    public List<ShoppingCartDto> getShoppingCarts() {
        return shoppingCartDtos;
    }

    public void setShoppingCarts(List<ShoppingCartDto> shoppingCartDtos) {
        this.shoppingCartDtos = shoppingCartDtos;
    }
}
