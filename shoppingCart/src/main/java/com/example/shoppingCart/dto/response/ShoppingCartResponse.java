package com.example.shoppingCart.dto.response;

import com.example.shoppingCart.dto.request.ItemRequest;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShoppingCartResponse {
    public Date dateCreated;
    public Long id;
    public List<ItemRequest> itemRequests;
}
