package com.devvengers.mjoraste.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler" , "orders"})

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;

    private String town;

    private String fullAddress;

    @OneToOne(mappedBy = "address")
    private User user;

    @OneToMany(mappedBy = "shippingAddress", cascade = CascadeType.ALL)
    private List<Order> orders;



}
