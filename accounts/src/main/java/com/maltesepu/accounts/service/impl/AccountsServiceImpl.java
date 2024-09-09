package com.maltesepu.accounts.service.impl;

import com.maltesepu.accounts.Constants.AccountsConst;
import com.maltesepu.accounts.dto.AccountsDto;
import com.maltesepu.accounts.dto.CustomerDto;
import com.maltesepu.accounts.entity.Accounts;
import com.maltesepu.accounts.entity.Customer;
import com.maltesepu.accounts.exception.CustomerAlreadyExistException;
import com.maltesepu.accounts.exception.ResourceNotFoundException;
import com.maltesepu.accounts.mapper.AccountsMapper;
import com.maltesepu.accounts.mapper.CustomerMapper;
import com.maltesepu.accounts.repository.AccountsRepo;
import com.maltesepu.accounts.repository.CustomerRepo;
import com.maltesepu.accounts.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private AccountsRepo accountsRepo;
    private CustomerRepo customerRepo;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepo.findByPhoneNumber(customerDto.getPhoneNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Phone number already used!");
        }
        Customer savedCustomer = customerRepo.save(customer);
        accountsRepo.save(createNewAccounts(savedCustomer));
    }

    private Accounts createNewAccounts(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 100000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConst.SAVINGS);
        newAccount.setBranchAddress(AccountsConst.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String phoneNumber) {
        Customer customer = customerRepo.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "phoneNumber", phoneNumber));

        Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepo.findById(accountsDto.getAccountNumber()).orElseThrow(() ->
                    new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber().toString()));

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepo.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepo.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepo.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String phoneNumber) {
        Customer customer = customerRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "PhoneNumber", phoneNumber));
        accountsRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteById(customer.getCustomerId());
        return true;
    }
}
