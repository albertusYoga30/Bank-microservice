package com.maltesepu.accounts.service;

import com.maltesepu.accounts.dto.CustomerDto;

public interface AccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String phoneNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String phoneNumber);
}
