package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.results.Result;
import com.devvengers.mjoraste.core.utilities.results.SuccessResult;
import com.devvengers.mjoraste.entities.PaymentType;
import com.devvengers.mjoraste.repository.PaymentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentTypeService {

    private PaymentTypeRepository paymentTypeRepository;

    public PaymentType findById(Long id) {
        PaymentType paymentType = paymentTypeRepository.findById(id).orElse(null);

        if (paymentType == null){
            return null;
        }else {
            return paymentType;
        }
    }


}
