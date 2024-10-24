package com.maltesepu.accounts.service.client;

import com.maltesepu.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardFeignClient {

    @GetMapping(value = "/api/cards/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(
            @RequestHeader("simplebank-correlation-id") String correlationId,
            @RequestParam String phoneNumber);
}
