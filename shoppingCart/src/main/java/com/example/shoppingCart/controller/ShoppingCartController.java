package com.example.shoppingCart.controller;

import com.example.shoppingCart.dto.Item;
import com.example.shoppingCart.dto.ShoppingCartDto;
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
    public ResponseEntity<ShoppingCartDto> save() {
        return status(HttpStatus.CREATED).body(shoppingCartService.save());
    }

    @GetMapping("/{cartId}")
    public ShoppingCartDto getItem(@PathVariable Long cartId){
        return cartProductService.findPProductByCartId(cartId);
    }

    @PutMapping("/{cartId}/{operationNumber}")
    public ShoppingCartDto addTocart(@PathVariable long cartId, @PathVariable Integer operationNumber , @RequestBody Item item){
        return cartProductService.save(cartId,operationNumber,item);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable long cartId){
        cartProductService.clearCart(cartId);
    }
}
