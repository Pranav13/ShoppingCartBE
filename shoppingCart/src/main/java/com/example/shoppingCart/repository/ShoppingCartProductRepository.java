package com.example.shoppingCart.repository;

import com.example.shoppingCart.model.ShoppingCart;
import com.example.shoppingCart.model.ShoppingCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, Long> {
    //ShoppingCartProduct save(ShoppingCartProduct product);
    List<ShoppingCartProduct> findByShoppingCart(ShoppingCart cart);
    ShoppingCartProduct findByProductId(Long id);
    ShoppingCartProduct findByShoppingCartId(Long id);
    void deleteByShoppingCartId(Long id);
}
