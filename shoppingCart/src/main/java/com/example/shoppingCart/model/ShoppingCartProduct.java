package com.example.shoppingCart.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_shoppingcart_product")
@Proxy(lazy=false)
public class ShoppingCartProduct {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name="shoppingcartId")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name="itemsId")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}