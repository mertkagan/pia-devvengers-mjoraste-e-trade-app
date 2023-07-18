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
        return new SuccessResult("The product has been successfully added to the cart");
    }


    public DataResult<GetUserCartResponse> getCart(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        calculateTotalPrice(user.getCart());
        Cart cart = user.getCart();
        GetUserCartResponse response = modelMapperService.forResponse().map(cart, GetUserCartResponse.class);
        return user != null ? new SuccessDataResult<GetUserCartResponse>(response,"Data retrieved succesfully")   : new ErrorDataResult<>(null,"No such user exists.");
    }

    @Transactional
    public Result deleteCartByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()){
            cartRepository.deleteByUserId(userId);
            return new SuccessResult("Cart deleted.");
        }else {
            return new ErrorResult("User not found");
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


