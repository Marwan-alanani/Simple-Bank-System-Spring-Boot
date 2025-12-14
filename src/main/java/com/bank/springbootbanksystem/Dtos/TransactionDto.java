package com.bank.springbootbanksystem.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDto {
    @NotBlank(message = "Transaction type is required.")
    private String transactionType;

    @NotNull(message = "Amount is required.")
    private Double transactionAmount;

    private Long sourceAccountId;

    private Long targetAccountId;
}
