package com.example.shoppingCart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_shoppingcart_product")
public class ShoppingCartProduct {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name="shoppingcartId")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name="itemsId")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;
}