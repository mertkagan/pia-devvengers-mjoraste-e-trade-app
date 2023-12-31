package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.*;
import com.devvengers.mjoraste.entities.Cart;
import com.devvengers.mjoraste.entities.CartItem;
import com.devvengers.mjoraste.entities.Product;
import com.devvengers.mjoraste.entities.User;
import com.devvengers.mjoraste.repository.CartItemRepository;
import com.devvengers.mjoraste.repository.CartRepository;
import com.devvengers.mjoraste.repository.ProductRepository;
import com.devvengers.mjoraste.repository.UserRepository;
import com.devvengers.mjoraste.service.requests.CreateCartItemRequest;
import com.devvengers.mjoraste.service.responses.GetUserCartResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private UserRepository userRepository;

    private ModelMapperService modelMapperService;

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    private ProductRepository productRepository;


    public Result addToCart(Long userId, CreateCartItemRequest createCartItemRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("Kullanıcı mevcut değil."));
        Product product = productRepository.findById(createCartItemRequest.getProductId()).orElseThrow(() -> new NullPointerException("Ürün mevcut değil."));

        if (user != null && product != null) {
            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                user.setCart(cart);
                cartRepository.save(cart);
            }

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            int quantity = createCartItemRequest.getQuantity();


            if (quantity <= 0) {
                return new ErrorResult("The item quantity is invalid.");
            }


            if (product.getStock() >= quantity) {
                cartItem.setQuantity(quantity);
                cartItem.setTotalItemPrice(quantity * product.getPrice());
                cartItem.setCart(cart);
                cartItemRepository.save(cartItem);


                cart.getCartItems().add(cartItem);




                cartRepository.save(cart);


                calculateTotalPrice(cart);


                userRepository.save(user);

                return new SuccessResult("The product has been successfully added to the cart.");
            } else {
                return new ErrorResult("Insufficient stock");
            }
        }

        return new ErrorResult("Something went wrong.");
    }


    public DataResult<GetUserCartResponse> getCart(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Cart cart = user.getCart();
        GetUserCartResponse response = modelMapperService.forResponse().map(cart, GetUserCartResponse.class);
        return user != null ? new SuccessDataResult<GetUserCartResponse>(response,"Data retrieved succesfully")   : new ErrorDataResult<>(null,"No such user exists.");
    }

    public Result removeCartItem(Long cartItemId) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            Cart cart = cartItem.getCart();


            double totalPrice = cart.getTotalPrice() - cartItem.getTotalItemPrice();
            cart.setTotalPrice(totalPrice);


            cart.getCartItems().remove(cartItem);


            cartRepository.save(cart);
            cartItemRepository.delete(cartItem);

            return new SuccessResult("Product removed from cart successfully.");
        } else {
            return new ErrorResult("CartItem not found.");
        }
    }


    @Transactional
    public Result deleteCartByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()){
            Cart cart = user.get().getCart();
            List<CartItem> cartItems = cart.getCartItems();
            cartItems.clear();
            cart.setTotalPrice(0);
            return new SuccessResult("Cart succesfully cleared.");
        }else {
            return new ErrorResult("User not found.");
        }

    }

    public void calculateTotalPrice(Cart cart) {
        double totalPrice = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getTotalItemPrice();
        }
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }

}


