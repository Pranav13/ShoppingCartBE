package com.example.shoppingCart.service;

import com.example.shoppingCart.dto.request.OrderRequest;
import com.example.shoppingCart.model.Order;
import com.example.shoppingCart.model.OrderProduct;
import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.model.Shipping;
import com.example.shoppingCart.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    OrderRepository orderRepository;

    public Order save(OrderRequest order) {
        // TODO Auto-generated method stub
        Order orderEntity = new Order();
        Shipping shippingEntity = new Shipping();
            long longdate = Long.parseLong(order.datePlaced);
            Date date = new Date(longdate);
            //Date simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(String.valueOf(date));
            orderEntity.setOrderdate(date);


        shippingEntity.setAddressLine1(order.shipping.addressLine1);
        shippingEntity.setAddressLine2(order.shipping.addressLine2);
        shippingEntity.setCity(order.shipping.city);
        shippingEntity.setName(order.shipping.name);

        orderEntity.setShipping(shippingEntity);

        orderEntity.setUserid(order.userId);

        List<OrderProduct> orderproduct = new ArrayList<>();


        List<OrderRequest.Item> item = Arrays.asList(order.items);
        for(OrderRequest.Item eachitem:item) {
            OrderProduct productEntity = new OrderProduct();
            productEntity.setImageUrl(eachitem.product.imageurl);
            productEntity.setPrice(eachitem.product.price);
            //productEntity.setTitle(eachitem.product.title);
            productEntity.setQuantity(eachitem.quantity);
            productEntity.setTotalprice(eachitem.product.price * eachitem.quantity );
            orderproduct.add(productEntity);
            orderEntity.setOrderproduct(orderproduct);
        }

        return orderRepository.save(orderEntity);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
}
