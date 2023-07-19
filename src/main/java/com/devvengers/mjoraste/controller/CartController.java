package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.Result;
import com.devvengers.mjoraste.entities.Cart;
import com.devvengers.mjoraste.service.concretes.CartService;
import com.devvengers.mjoraste.service.requests.CreateCartItemRequest;
import com.devvengers.mjoraste.service.responses.GetUserCartResponse;
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
    public Result addToCart(@RequestParam Long userId, @RequestBody CreateCartItemRequest cartItemRequest) {
        return cartService.addToCart(userId, cartItemRequest);

    }

    @GetMapping("/{userId}")
    public DataResult<GetUserCartResponse> getCart(@PathVariable Long userId) {
        //art cart = cartService.getCart(userId);
        return cartService.getCart(userId);
    }

    @DeleteMapping("/removeCartItem/{cartItemId}")
    public Result deleteCartItem(@PathVariable Long cartItemId){
        return cartService.removeCartItem(cartItemId);
    }

    @DeleteMapping("/deleteCart")
    public Result deleteCartByUserId(Long userId){
        return cartService.deleteCartByUserId(userId);
    }
}
