package com.example.shoppingCart.service;

import com.example.shoppingCart.dto.response.ShoppingCartResponse;
import com.example.shoppingCart.model.ShoppingCart;
import com.example.shoppingCart.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartResponse save() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDatecreated(new Date());
        ShoppingCart derivedCart = shoppingCartRepository.save(shoppingCart);
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
        shoppingCartResponse.setDateCreated(derivedCart.getDatecreated());
        shoppingCartResponse.setId(derivedCart.getId());
        return shoppingCartResponse;
    }
}
