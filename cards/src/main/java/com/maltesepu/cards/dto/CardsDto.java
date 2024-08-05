package com.maltesepu.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {

    @NotEmpty(message = "Phone number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid phone number format!")
    private String phoneNumber;

    @NotEmpty(message = "Card number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Invalid card number format!")
    private String cardNumber;

    @NotEmpty(message = "Card type can't be null or empty")
    private String cardType;

    @Positive(message = "Total card should be greater than zero")
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount used should be equal or greater than zero")
    private int availableAmount;
}
