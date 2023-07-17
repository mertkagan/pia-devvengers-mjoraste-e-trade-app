package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.entities.Cart;
import com.devvengers.mjoraste.service.concretes.CartService;
import com.devvengers.mjoraste.service.requests.CreateCartItemRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @PostMapping("/{userId}/add")
    public ResponseEntity<String> addToCart(@RequestParam Long userId, @RequestBody CreateCartItemRequest cartItemRequest) {
        cartService.addToCart(userId, cartItemRequest);
        System.out.println(cartItemRequest);
        System.out.println(userId);
        return ResponseEntity.ok("Product added to cart successfully.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }
}
