package com.example.shoppingCart.dto;

import java.util.ArrayList;

public final class OrderDto {
    private String userId;
    Shipping ShippingObject;
    private float datePlaced;
    ArrayList< Object > items = new ArrayList < Object > ();


    // Getter Methods

    public String getUserId() {
        return userId;
    }

    public Shipping getShipping() {
        return ShippingObject;
    }

    public float getDatePlaced() {
        return datePlaced;
    }

    // Setter Methods

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setShipping(Shipping shippingObject) {
        this.ShippingObject = shippingObject;
    }

    public void setDatePlaced(float datePlaced) {
        this.datePlaced = datePlaced;
    }
}
class Shipping {
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;


    // Getter Methods

    public String getName() {
        return name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
