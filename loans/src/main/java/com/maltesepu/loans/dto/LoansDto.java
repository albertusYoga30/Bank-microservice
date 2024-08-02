package com.maltesepu.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loans", description = "Schema to hold loans information")
public class LoansDto {

    @NotEmpty(message = "Phone number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits")
    @Schema(description = "Phone number of the customer", example = "0822767820")
    private String phoneNumber;

    @NotEmpty(message = "Loan number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "LoanNumber must be 12 digits")
    @Schema(description = "Loan number of the customer", example = "8000000")
    private String loanNumber;

    @NotEmpty(message = "Loan type can't be null or empty")
    @Schema(description = "Type of loan", example = "Home loan")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(description = "Total loan amount", example = "500000")
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(description = "Total loan amount paid", example = "1000")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(description = "Total outstanding amount against a loan", example = "99999")
    private int outstandingAmount;
}
