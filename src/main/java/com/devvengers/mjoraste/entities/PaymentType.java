package com.devvengers.mjoraste.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @OneToMany(mappedBy = "paymentType")
    @JsonIgnore
    private List<Order> orders;
}
