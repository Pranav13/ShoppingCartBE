package com.example.shoppingCart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_Order_Product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "price")
    private Long price;

    @Column(name = "totalprice")
    private Long totalprice;

    @Column(name = "quantity")
    private Long quantity;

}
