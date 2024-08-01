package com.maltesepu.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold account information"
)
public class AccountsDto {

    @Schema(description = "Account number of simple bank", example = "1928357281")
    @NotEmpty(message = "Account Number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Account type of simple bank account", example = "savings")
    @NotEmpty(message = "Account Type can't be null or empty")
    private String accountType;

    @Schema(description = "Branch address of simple bank",example = "Jakarta selatan")
    @NotEmpty(message = "Branch Address Type can't be null or empty")
    private String branchAddress;
}
