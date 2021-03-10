package com.example.shoppingCart.dto.request;

import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public final class OrderRequest {
    public  String datePlaced;
    public  Shipping shipping;
    public  Item items[];
    public  String userId;


    public OrderRequest(String datePlaced, Shipping shipping, Item[] items, String userId){
        this.datePlaced = datePlaced;
        this.shipping = shipping;
        this.items = items;
        this.userId = userId;
    }

    public static final class Shipping {
        public  String name;
        public  String addressLine1;
        public  String addressLine2;
        public  String city;

        public Shipping() {
        }

        public Shipping(String name, String addressLine1, String addressLine2, String city){
            this.name = name;
            this.addressLine1 = addressLine1;
            this.addressLine2 = addressLine2;
            this.city = city;
        }

        @Override
        public String toString() {
            return "Shipping [name=" + name + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
                    + ", city=" + city + "]";
        }

    }

    public static final class Item {
        public  Product product;
        public  long quantity;

        public Item() {
        }

        public Item(Product product, long quantity){
            this.product = product;
            this.quantity = quantity;
        }

        public static final class Product {
            public  String title;
            public  String imageurl;
            public  long price;

            public Product() {
            }

            public Product(String title, String imageUrl, long price){
                this.title = title;
                this.imageurl = imageUrl;
                this.price = price;
            }

            @Override
            public String toString() {
                return "Product [title=" + title + ", imageUrl=" + imageurl + ", price=" + price + "]";
            }
        }
    }


}

