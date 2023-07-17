package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.Result;
import com.devvengers.mjoraste.core.utilities.results.SuccessResult;
import com.devvengers.mjoraste.entities.Cart;
import com.devvengers.mjoraste.entities.CartItem;
import com.devvengers.mjoraste.entities.Product;
import com.devvengers.mjoraste.entities.User;
import com.devvengers.mjoraste.repository.CartItemRepository;
import com.devvengers.mjoraste.repository.CartRepository;
import com.devvengers.mjoraste.repository.ProductRepository;
import com.devvengers.mjoraste.repository.UserRepository;
import com.devvengers.mjoraste.service.requests.CreateCartItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {

    private UserRepository userRepository;

    private ModelMapperService modelMapperService;

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    private ProductRepository productRepository;


    public void addToCart(Long userId, CreateCartItemRequest createCartItemRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("Kullanıcı mevcut değil."));
        Product product = productRepository.findById(createCartItemRequest.getProductId()).orElseThrow(() -> new NullPointerException("Ürün mevcut değil."));

        if (user != null && product != null) {
            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                user.setCart(cart);
                // Cart nesnesini önce kaydediyoruz
                cartRepository.save(cart); // Cart nesnesini kaydedin
            }

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(createCartItemRequest.getQuantity());
            cartItem.setTotalItemPrice( (createCartItemRequest.getQuantity()*product.getPrice()) );
            cartItem.setCart(cart);

            // CartItem nesnesini önce kaydediyoruz

            cartItemRepository.save(cartItem);

            // Cart nesnesine CartItem ekleyip veritabanında güncelliyoruz
            cart.getCartItems().add(cartItem);

            cartRepository.save(cart);

            // User nesnesini güncelliyoruz
            userRepository.save(user);



        }

    }


    public Cart getCart(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        calculateTotalPrice(user.getCart());
        return user != null ? user.getCart()   : null;
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


