package com.example.shoppingCart.controller;

import com.example.shoppingCart.dto.Item;
import com.example.shoppingCart.dto.ProductRequest;
import com.example.shoppingCart.dto.ShoppingCartItem;
import com.example.shoppingCart.model.ShoppingCart;
import com.example.shoppingCart.service.ShoppingCartProductService;
import com.example.shoppingCart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartProductService cartProductService;

    @PostMapping
    public ResponseEntity<ShoppingCart> save() {
        return status(HttpStatus.CREATED).body(shoppingCartService.save());
    }

    /*@PostMapping
    public ResponseEntity<ShoppingCart> saveProduct(ShoppingCartItem shoppingCartItem){
        //CartProductService.save()
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    @GetMapping("/{cartId}")
    public com.example.shoppingCart.dto.ShoppingCart getItem(@PathVariable Long cartId){
        return cartProductService.findPProductByCartId(cartId);
    }

    @PutMapping("/{cartId}/{operationNumber}")
    public  com.example.shoppingCart.dto.ShoppingCart addTocart(@PathVariable long cartId, @PathVariable Integer operationNumber ,@RequestBody Item item){
        return cartProductService.save(cartId,operationNumber,item);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable long cartId){
        cartProductService.clearCart(cartId);
    }
}
