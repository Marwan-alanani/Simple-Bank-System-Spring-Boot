package com.bank.springbootbanksystem.service.IService;


import com.bank.springbootbanksystem.Dtos.TransactionDto;
import com.bank.springbootbanksystem.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface ITransactionService {
    Transaction handleDeposit(TransactionDto dto);
    Transaction handleWithdrawal(TransactionDto dto);
    Transaction handleTransfer(TransactionDto dto);
    List<Transaction> getAccountTransactions(Long accountId);
    List<Transaction> getTransactionsByDateRange(LocalDateTime start, LocalDateTime end, String type);

}
