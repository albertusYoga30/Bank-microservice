package com.maltesepu.accounts.service.client;

import com.maltesepu.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardFeignClient{

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String phoneNumber) {
        return null;
    }
}
