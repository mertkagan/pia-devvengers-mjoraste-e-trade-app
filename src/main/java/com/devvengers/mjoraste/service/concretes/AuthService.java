package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.*;
import com.devvengers.mjoraste.entities.Address;
import com.devvengers.mjoraste.entities.User;
import com.devvengers.mjoraste.repository.AddressRepository;
import com.devvengers.mjoraste.repository.UserRepository;
import com.devvengers.mjoraste.service.requests.AddressGetRequest;
import com.devvengers.mjoraste.service.requests.AddressRequest;
import com.devvengers.mjoraste.service.requests.LoginRequest;
import com.devvengers.mjoraste.service.requests.RegisterRequest;
import com.devvengers.mjoraste.service.responses.AddressResponse;
import com.devvengers.mjoraste.service.responses.LoginResponse;
import lombok.AllArgsConstructor;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AuthService {
    final String  SUCCESS = "SUCCESSFUL";
    final String  UNSUCCESS = "UNSUCCESSFUL";

    private UserRepository userRepository;
    private AddressRepository addressRepository;

    private ModelMapperService modelMapperService;

    private PasswordEncoder passwordEncoder;


    public DataResult userRegister(@Valid RegisterRequest userRegisterRequest, BindingResult bindingResult) {
        String errorMessage = "";
        DataResult result;
        User existingUserByEmail = userRepository.findByEmail(userRegisterRequest.getEmail());
        User existingUserByPhoneNumber = userRepository.findByPhoneNumber(userRegisterRequest.getPhoneNumber());


        if (existingUserByEmail != null) {
            String message = "Girilen e-mail zaten kayıtlı! ";
            return new ErrorDataResult(message, UNSUCCESS);
        }
        if (existingUserByPhoneNumber != null) {
            String message = "Girilen telefon zaten kayıtlı! ";
            return new ErrorDataResult(message, UNSUCCESS);
        }
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("name")) {
                errorMessage += "İsim alanı boş bırakılamaz. ";
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
            result = new ErrorDataResult(errorMessage, UNSUCCESS);
        } else {

            String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());


            User newUser = new User();
            newUser.setIsAdmin(false);
            newUser.setEmail(userRegisterRequest.getEmail());
            newUser.setName(userRegisterRequest.getName());
            newUser.setSurName(userRegisterRequest.getSurName());
            newUser.setPhoneNumber(userRegisterRequest.getPhoneNumber());
            newUser.setPassword(encodedPassword);

            userRepository.save(newUser);

            // Başarılı kayıt mesajı
            String SuccMessage = "User created succesfully.";
            result = new SuccessDataResult(SuccMessage, SUCCESS);
        }
        return result;
    }


    public DataResult<LoginResponse> userLogin(LoginRequest userLoginRequest) {

        User user = userRepository.findByEmail(userLoginRequest.getEmail());

        if (user == null) {
            return new ErrorDataResult<>(null,"Username or password is incorrect");
        }

        String password = userLoginRequest.getPassword();
        String encodedPassword = user.getPassword();
        Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

        if (isPwdRight){
            Optional<User> user1 = userRepository.findOneByEmailAndPassword(userLoginRequest.getEmail(),encodedPassword);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(user.getId());
            loginResponse.setName(user.getName());
            loginResponse.setSurName(user.getSurName());
            loginResponse.setEmail(user.getEmail());
            loginResponse.setPhoneNumber(user.getPhoneNumber());
            loginResponse.setIsAdmin(user.getIsAdmin());
            return new SuccessDataResult<LoginResponse>(loginResponse,"Login succesfully.");
        }

        return new ErrorDataResult<>(null,"Username or password is incorrect");
    }


}
