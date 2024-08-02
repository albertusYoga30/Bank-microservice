package com.maltesepu.loans.repository;

import com.maltesepu.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepo extends JpaRepository<Loans, Long> {

    Optional<Loans> findByPhoneNumber(String phoneNumber);

    Optional<Loans> findByLoanNumber(String loanNumber);
}
