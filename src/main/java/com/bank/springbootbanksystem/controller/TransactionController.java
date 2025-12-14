package com.bank.springbootbanksystem.controller;

import com.bank.springbootbanksystem.Dtos.TransactionDto;
import com.bank.springbootbanksystem.model.Transaction;
import com.bank.springbootbanksystem.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor

public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> makeTransaction(@Valid @RequestBody TransactionDto dto) {
        Transaction t = transactionService.makeTransaction(dto);
        return ResponseEntity.status(201).body(t);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountTransactions(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getAccountTransactions(accountId));
    }

    @GetMapping("/date-range")
    public ResponseEntity<?> getTransactionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) String transactionType) {
        return ResponseEntity.ok(transactionService.getTransactionsByDateRange(startDate, endDate, transactionType));
    }
}
