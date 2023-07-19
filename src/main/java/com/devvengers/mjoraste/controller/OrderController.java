package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.core.utilities.results.Result;
import com.devvengers.mjoraste.service.concretes.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("purchase")
    public Result createOrder(@RequestParam Long userId, @RequestParam Long paymentTypeId) {
        return orderService.createOrder(userId,paymentTypeId);
    }
}
