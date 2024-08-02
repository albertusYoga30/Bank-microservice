package com.maltesepu.loans.service;

import com.maltesepu.loans.constants.LoansConstants;
import com.maltesepu.loans.dto.LoansDto;
import com.maltesepu.loans.entity.Loans;
import com.maltesepu.loans.exception.LoanAlreadyExistsException;
import com.maltesepu.loans.exception.ResourceNotFoundException;
import com.maltesepu.loans.mapper.LoansMapper;
import com.maltesepu.loans.repository.LoansRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements LoansService {

    private LoansRepo loansRepo;

    @Override
    public void createLoan(String phoneNumber) {
        Optional<Loans> optionalLoans = loansRepo.findByPhoneNumber(phoneNumber);
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given phone number " + phoneNumber);
        }
        Loans newLoan = new Loans();
        long loanNumber = 100000000000L + new Random().nextInt(900000000);

        newLoan.setLoanNumber(Long.toString(loanNumber));
        newLoan.setPhoneNumber(phoneNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        loansRepo.save(newLoan);
    }

    @Override
    public LoansDto fetchLoan(String phoneNumber) {
        Loans loans = loansRepo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Phone number", phoneNumber)
        );
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepo.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Loan number", loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepo.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String phoneNumber) {
        Loans loans = loansRepo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Phone number", phoneNumber)
        );
        loansRepo.deleteById(loans.getLoanId());
        return true;
    }
}
