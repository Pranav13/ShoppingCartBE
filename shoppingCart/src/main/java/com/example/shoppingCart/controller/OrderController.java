package com.example.shoppingCart.controller;


import com.example.shoppingCart.dto.OrderDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    public void save(@RequestBody OrderDto orderDto) {
        System.out.println(orderDto);
    }
}
