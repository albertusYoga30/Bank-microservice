package com.maltesepu.loans.service;

import com.maltesepu.loans.dto.LoansDto;

public interface LoansService {

    void createLoan(String phoneNumber);

    LoansDto fetchLoan(String phoneNumber);

    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(String phoneNumber);
}
