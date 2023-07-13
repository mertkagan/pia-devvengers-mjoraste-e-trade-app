package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.entities.User;
import com.devvengers.mjoraste.repository.UserRepository;
import com.devvengers.mjoraste.service.requests.LoginRequest;
import com.devvengers.mjoraste.service.requests.RegisterRequest;
import com.devvengers.mjoraste.service.responses.LoginResponse;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;

    private ModelMapperService modelMapperService;

    private PasswordEncoder passwordEncoder;


    public String userRegister(@Valid RegisterRequest userRegisterRequest, BindingResult bindingResult) {
        String errorMessage = "";
        User existingUserByEmail = userRepository.findByEmail(userRegisterRequest.getEmail());
        User existingUserByPhoneNumber = userRepository.findByPhoneNumber(userRegisterRequest.getPhoneNumber());


        if (existingUserByEmail != null) {
            return "Girilen e-mail zaten kayıtlı! ";
        }
        if (existingUserByPhoneNumber != null) {
            return "Girilen telefon numarası zaten kayıtlı! ";
        }
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("name")) {
                errorMessage += "Name alanı boş bırakılamaz. ";
            }
            if (bindingResult.hasFieldErrors("email")) {
                errorMessage += "E-mail alanı boş bırakılamaz. ";
            }
            if (bindingResult.hasFieldErrors("surName")) {
                errorMessage += "Soyad alanı boş bırakılamaz. ";
            }
            if (bindingResult.hasFieldErrors("phoneNumber")) {
                errorMessage += "Telefon alanı boş bırakılamaz. ";
            }
            if (bindingResult.hasFieldErrors("password")) {
                errorMessage += "Şifre alanı boş bırakılamaz. ";
            }
            return errorMessage;
        } else {

            String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());


            User newUser = new User();
            newUser.setEmail(userRegisterRequest.getEmail());
            newUser.setName(userRegisterRequest.getName());
            newUser.setSurName(userRegisterRequest.getSurName());
            newUser.setPhoneNumber(userRegisterRequest.getPhoneNumber());
            newUser.setPassword(encodedPassword);

            userRepository.save(newUser);

            // Başarılı kayıt mesajı
            return "User created succesfully.";
        }
    }


    public LoginResponse userLogin(LoginRequest userLoginRequest, BindingResult bindingResult) {

        User existingUserByEmail = userRepository.findByEmail(userLoginRequest.getEmail());

        if (existingUserByEmail != null) {
            String password = userLoginRequest.getPassword();
            String encodedPassword = existingUserByEmail.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                Optional<User> loggedInUser = userRepository.findOneByEmailAndPassword(userLoginRequest.getEmail(), encodedPassword);

                if (loggedInUser.isPresent()) {
                    User user = loggedInUser.get();
                    return new LoginResponse("Login successful", user.getId(), user.getName(), user.getSurName(), user.getEmail(), user.getPhoneNumber());
                }
            }
        }

        return new LoginResponse("Login failed", null, null, null, null, null);



    }

}
