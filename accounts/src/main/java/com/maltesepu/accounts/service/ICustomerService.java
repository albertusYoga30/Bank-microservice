package com.maltesepu.accounts.service;

import com.maltesepu.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String phoneNumber, String correlationId);
}
