package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.*;
import com.devvengers.mjoraste.entities.*;
import com.devvengers.mjoraste.repository.*;
import com.devvengers.mjoraste.service.requests.CreateOrderRequest;
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
import java.util.Random;
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

    private AddressRepository addressRepository;

    public Result createOrder(CreateOrderRequest createOrderRequest){
        User user = userRepository.findById(createOrderRequest.getUserId()).orElse(null);

        if (user == null) {
            return new ErrorResult("User not found");
        }

        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();

        PaymentType paymentType = paymentTypeService.findById(createOrderRequest.getPaymentTypeId());

        Address shippingAddress = new Address();
        shippingAddress.setCity(createOrderRequest.getCity());
        shippingAddress.setTown(createOrderRequest.getTown());
        shippingAddress.setFullAddress(createOrderRequest.getFullAddress());
        shippingAddress.setUser(user);
        addressRepository.save(shippingAddress);


        // Sipariş oluştur
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderCode(generateOrderCode());
        List<OrderItem> orderItems = new ArrayList<>();
        order.setPaymentType(paymentType);
        order.setShippingAddress(shippingAddress);

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
        order.setOrderStatus(false);
        order.setTotalOrderPrice(cart.getTotalPrice());
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

    private String generateOrderCode() {
        Random random = new Random();
        StringBuilder orderCode = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(10); // 0 ile 9 arasında rastgele bir sayı alınır
            orderCode.append(randomNumber); // String'e eklenir
        }

        return orderCode.toString();
    }

    public Result updateOrderStatus(Long orderId) {

        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            return new ErrorResult("Order not found");
        }

        User user = order.getUser();
        if (user == null) {
            return new ErrorResult("User not found");
        }

        Boolean isAdmin = user.getIsAdmin();
        if (isAdmin == null || !isAdmin) {
            return new ErrorResult("Unauthorized: Only admins can update order status.");
        }

        Boolean orderStatus = order.getOrderStatus();
        if (!orderStatus) {
            order.setOrderStatus(true);
            orderRepository.save(order);
            return new SuccessResult("Status updated successfully.");
        }

        return new ErrorResult("Something went wrong");
    }
}