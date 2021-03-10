package com.example.shoppingCart.controller;

import com.example.shoppingCart.dto.request.ItemRequest;
import com.example.shoppingCart.dto.response.ShoppingCartResponse;
import com.example.shoppingCart.service.ShoppingCartProductService;
import com.example.shoppingCart.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/shopping-carts")
@AllArgsConstructor
public class ShoppingCartController {

    ShoppingCartService shoppingCartService;

    ShoppingCartProductService cartProductService;

    @PostMapping
    public ResponseEntity<ShoppingCartResponse> save() {
        return status(HttpStatus.CREATED).body(shoppingCartService.save());
    }

    @GetMapping("/{cartId}")
    public ShoppingCartResponse getItem(@PathVariable Long cartId){
        return cartProductService.findPProductByCartId(cartId);
    }

    @PutMapping("/{cartId}/{operationNumber}")
    public ShoppingCartResponse addTocart(@PathVariable long cartId, @PathVariable Integer operationNumber , @RequestBody ItemRequest itemRequest){
        return cartProductService.save(cartId,operationNumber, itemRequest);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable long cartId){
        cartProductService.clearCart(cartId);
    }
}
