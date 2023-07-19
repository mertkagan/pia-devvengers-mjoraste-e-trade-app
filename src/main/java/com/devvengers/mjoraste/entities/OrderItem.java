package com.devvengers.mjoraste.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="quantity")
    private int quantity;

    @Column(name = "total_price")
    private Double totalItemPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
