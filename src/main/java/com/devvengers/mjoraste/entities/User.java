package com.devvengers.mjoraste.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;

    private String surName;

    private String email;

    private String phoneNumber;

    private String password;

    private List<Address> addresses;

    private List<Card> card;

    private List<Order> orders;




}
