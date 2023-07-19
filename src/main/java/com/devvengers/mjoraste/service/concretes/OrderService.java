package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.*;
import com.devvengers.mjoraste.entities.*;
import com.devvengers.mjoraste.repository.*;
import com.devvengers.mjoraste.service.responses.GetUserCartResponse;
import com.devvengers.mjoraste.service.responses.GetUserOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private PaymentTypeService paymentTypeService;

    private CartItemRepository cartItemRepository;

    private CartRepository cartRepository;

    private ProductRepository productRepository;

    private OrderItemRepository orderItemRepository;

    private ModelMapperService modelMapperService;

    public Result createOrder(Long userId,Long paymentTypeId){
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return new ErrorResult("User not found");
        }

        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();

        PaymentType paymentType = paymentTypeService.findById(paymentTypeId);


        // Sipariş oluştur
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        List<OrderItem> orderItems = new ArrayList<>();
        order.setPaymentType(paymentType);

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            if (product.getStock() >= quantity) {
                // Stok yeterliyse, sipariş öğesi oluştur ve stoktan ürün adedini düşür
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setQuantity(quantity);
                orderItem.setTotalItemPrice(cartItem.getTotalItemPrice());
                orderItems.add(orderItem);
                orderItem.setOrder(order);


                product.setStock(product.getStock() - quantity);
                productRepository.save(product);
            } else {
                return new ErrorResult("Stock Error"); // Stok yetersizse sipariş oluşturma işlemi başarısız olur
            }
        }


        order.setOrderItems(orderItems);
        cartItems.clear();
        cart.setTotalPrice(0);
        userRepository.save(user);
        orderRepository.save(order);




        return new SuccessResult("Created");
    }


    public DataResult<List<GetUserOrderResponse>> getUserOrders(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Order> orders = user.getOrders();
        List<GetUserOrderResponse> response = orders.stream()
                .map(order -> this.modelMapperService.forResponse().map(order, GetUserOrderResponse.class))
                .collect(Collectors.toList());

        return user != null ? new SuccessDataResult<List<GetUserOrderResponse>>(response,"Data retrieved succesfully")   : new ErrorDataResult<>(null,"No such user exists.");
    }


}
