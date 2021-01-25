package com.example.shoppingCart.repository;

import com.example.shoppingCart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    //ShoppingCartItem save(ShoppingCartItem item);
}
