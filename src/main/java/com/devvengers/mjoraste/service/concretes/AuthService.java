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
import java.util.Optional;


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
            String message = "Girilen e-mail zaten kayıtlı! ";
            return new ErrorDataResult(message, UNSUCCESS);
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
            result = new ErrorDataResult(errorMessage, UNSUCCESS);
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
            String SuccMessage = "User created succesfully.";
            result = new SuccessDataResult(SuccMessage, SUCCESS);
        }
        return result;
    }


    public DataResult userLogin(LoginRequest userLoginRequest, BindingResult bindingResult) {

        User existingUserByEmail = userRepository.findByEmail(userLoginRequest.getEmail());
        LoginResponse lr;
        if (existingUserByEmail != null) {
            String password = userLoginRequest.getPassword();
            String encodedPassword = existingUserByEmail.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                Optional<User> loggedInUser = userRepository.findOneByEmailAndPassword(userLoginRequest.getEmail(), encodedPassword);

                if (loggedInUser.isPresent()) {
                    User user = loggedInUser.get();
                    lr = new LoginResponse("Login successful", user.getId(), user.getName(), user.getSurName(), user.getEmail(), user.getPhoneNumber());
                    return new SuccessDataResult(lr, "Başarılı"); }
            }
        }
        lr = new LoginResponse("Login failed", null, null, null, null, null);
        return new SuccessDataResult(lr, "Login failed");
    }

    public Result getUserAddress(AddressGetRequest addressRequest, BindingResult bindingResult) {
        User user = userRepository.findById(addressRequest.getUserId());
        Address resultAddress = user.getAddress();
        AddressResponse ar;
        if (resultAddress != null) {
            ar = new AddressResponse();
            ar.setId(resultAddress.getId());
            ar.setTown(resultAddress.getTown());
            ar.setFullAddress(resultAddress.getFullAddress());
            ar.setCity(resultAddress.getCity());
            return new SuccessDataResult(ar, SUCCESS);
        }
        return new ErrorResult(UNSUCCESS);
    }

    public DataResult setUserAddress(AddressRequest addressRequest, BindingResult bindingResult) {
        //AddressRequest
        boolean error = false;
        DataResult result;

        if (bindingResult.hasErrors()) {
            result = new ErrorDataResult(UNSUCCESS);
        } else {
            Address address = new Address();
            address.setFullAddress(address.getFullAddress());
            address.setCity(address.getCity());
            address.setTown(address.getTown());
            address.setUser(addressRequest.getUser());

            addressRepository.save(address);
            User user = userRepository.findById(addressRequest.getUser().getId());
            user.setAddress(address);
            userRepository.save(user);
            result = new SuccessDataResult(SUCCESS);
        }

        return result;
    }

}
