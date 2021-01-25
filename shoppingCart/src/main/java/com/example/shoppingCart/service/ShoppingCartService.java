package com.example.shoppingCart.service;

import com.example.shoppingCart.model.ShoppingCart;
import com.example.shoppingCart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart save() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDatecreated(new Date());
        return shoppingCartRepository.save(shoppingCart);
    }
}
