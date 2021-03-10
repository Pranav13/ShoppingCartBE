package com.example.shoppingCart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_Order")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "orderdate")
    private Date orderdate;

   // @JsonIgnore
    @JoinColumn(name= "order_id")
    @OneToMany(cascade = CascadeType.ALL)
    List<OrderProduct> orderproduct = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Shipping shipping;

}
