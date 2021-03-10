package com.example.shoppingCart.controller;


import com.example.shoppingCart.dto.request.OrderRequest;
import com.example.shoppingCart.dto.response.ProductResponse;
import com.example.shoppingCart.model.Order;
import com.example.shoppingCart.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody OrderRequest orderRequest) {
        return status(HttpStatus.CREATED).body(orderService.save(orderRequest));

    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder() {
        return status(HttpStatus.OK).body(orderService.getAllOrder());
    }
}
