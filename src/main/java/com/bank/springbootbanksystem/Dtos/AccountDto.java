package com.bank.springbootbanksystem.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {
    @NotBlank(message = "Account number is required.")
    private String accountNumber;

    @NotNull(message = "Balance is required.")
    @Min(value = 0, message = "Balance cannot be negative")
    private Double accountBalance;

    @NotBlank(message = "Account type is required.")
    @Pattern(regexp = "SAVINGS|CHECKING", message = "Account type must be either 'SAVINGS' or 'CHECKING'.")
    private String accountType;

    @NotNull(message = "User ID is required.")
    private Long userId;

}
