package com.maltesepu.accounts.service.impl;

import com.maltesepu.accounts.dto.AccountsDto;
import com.maltesepu.accounts.dto.CardsDto;
import com.maltesepu.accounts.dto.CustomerDetailsDto;
import com.maltesepu.accounts.dto.LoansDto;
import com.maltesepu.accounts.entity.Accounts;
import com.maltesepu.accounts.entity.Customer;
import com.maltesepu.accounts.exception.ResourceNotFoundException;
import com.maltesepu.accounts.mapper.AccountsMapper;
import com.maltesepu.accounts.mapper.CustomerMapper;
import com.maltesepu.accounts.repository.AccountsRepo;
import com.maltesepu.accounts.repository.CustomerRepo;
import com.maltesepu.accounts.service.ICustomerService;
import com.maltesepu.accounts.service.client.CardFeignClient;
import com.maltesepu.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomerService {

    private AccountsRepo accountsRepo;
    private CustomerRepo customerRepo;
    private CardFeignClient cardFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String phoneNumber, String correlationId) {
        Customer customer = customerRepo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "phoneNumber", phoneNumber));

        Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));


        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer);
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, phoneNumber);
        if (loansDtoResponseEntity != null) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardFeignClient.fetchCardDetails(correlationId, phoneNumber);
        if (cardsDtoResponseEntity != null) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;

    }
}
