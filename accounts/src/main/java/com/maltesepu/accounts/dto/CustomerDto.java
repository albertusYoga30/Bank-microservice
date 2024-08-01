package com.maltesepu.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and account information")
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "Alessandra")
    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5 and 30")
    private String name;

    @Schema(description = "Email of the customer", example = "alessandra@gmail.com")
    @NotEmpty(message = "Email address can't be null or empty")
    @Email(message = "Email address format isn't valid")
    private String email;

    @Schema(description = "Phone number of the customer", example = "082279961886")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String phoneNumber;

    @Schema(description = "Account details of the customer")
    private AccountsDto accountsDto;
}
