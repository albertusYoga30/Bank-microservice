package com.maltesepu.accounts.service.client;

import com.maltesepu.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallBack implements LoansFeignClient {


    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId, String phoneNumber) {
        return null;
    }
}
