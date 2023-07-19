package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.Result;
import com.devvengers.mjoraste.service.concretes.OrderService;
import com.devvengers.mjoraste.service.requests.CreateOrderRequest;
import com.devvengers.mjoraste.service.responses.GetUserOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public DataResult<List<GetUserOrderResponse>> getUserorders(@RequestParam Long userId) {
        return orderService.getUserOrders(userId);
    }

    @PostMapping("purchase")
    public Result createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.createOrder(createOrderRequest);
    }

    @PutMapping("/updateStatus")
    public Result updateOrderStatus(@RequestParam Long orderId){
        return orderService.updateOrderStatus(orderId);
    }
}
