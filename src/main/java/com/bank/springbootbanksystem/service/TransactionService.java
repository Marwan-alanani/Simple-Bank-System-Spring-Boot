package com.bank.springbootbanksystem.service;

import com.bank.springbootbanksystem.Dtos.AccountDto;
import com.bank.springbootbanksystem.Dtos.TransactionDto;
import com.bank.springbootbanksystem.model.Account;
import com.bank.springbootbanksystem.model.Branch;
import com.bank.springbootbanksystem.model.Transaction;
import com.bank.springbootbanksystem.repository.AccountRepository;
import com.bank.springbootbanksystem.repository.TransactionRepository;
import com.bank.springbootbanksystem.service.IService.IAccountService;
import com.bank.springbootbanksystem.service.IService.ITransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;


    @Transactional
    public Transaction makeTransaction(TransactionDto dto){
        String type = dto.getTransactionType().toUpperCase();
        switch (type){
            case "DEPOSIT":
                return handleDeposit(dto);
            case "WITHDRAWAL":
                return handleWithdrawal(dto);
            case "TRANSFER":
                return handleTransfer(dto);
            default:
                throw new IllegalArgumentException("Invalid transaction type");
        }
    }
    @Override
    public Transaction handleDeposit(TransactionDto dto) {
        if (dto.getTargetAccountId() == null) throw new IllegalArgumentException("Invalid target account id");
        Account targetAccount = accountRepository.findById(dto.getTargetAccountId()).orElseThrow(() -> new IllegalArgumentException("Invalid target account id"));
        targetAccount.setAccountBalance(targetAccount.getAccountBalance()+ dto.getTransactionAmount());
        accountRepository.save(targetAccount);

        Transaction t = Transaction.builder()
                .transactionType("DEPOSIT")
                .transactionAmount(dto.getTransactionAmount())
                .sourceAccount(null)
                .targetAccount(targetAccount)
                .build();
        return transactionRepository.save(t);

    }

    @Override
    public Transaction handleWithdrawal(TransactionDto dto) {
        if (dto.getSourceAccountId() == null) throw new IllegalArgumentException("Source account required for withdrawal");
        Account source = accountRepository.findById(dto.getSourceAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));

        if (source.getAccountBalance() < dto.getTransactionAmount()) {
            throw new IllegalArgumentException(String.format("Insufficient funds. Available: %.2f, Requested: %.2f", source.getAccountBalance(), dto.getTransactionAmount()));
        }
        source.setAccountBalance(source.getAccountBalance() - dto.getTransactionAmount());
        accountRepository.save(source);

        Transaction t = Transaction.builder()
                .transactionType("WITHDRAWAL")
                .transactionAmount(dto.getTransactionAmount())
                .sourceAccount(source)
                .targetAccount(null)
                .build();
        return transactionRepository.save(t);
    }

    @Override
    public Transaction handleTransfer(TransactionDto dto) {
        if (dto.getSourceAccountId() == null || dto.getTargetAccountId() == null)
            throw new IllegalArgumentException("Both accounts required for transfer");
        if (dto.getSourceAccountId().equals(dto.getTargetAccountId()))
            throw new IllegalArgumentException("Cannot transfer to the same account");

        Account source = accountRepository.findById(dto.getSourceAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));
        Account target = accountRepository.findById(dto.getTargetAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Target account not found"));

        Long sourceUserId = source.getUser().getId();
        Long targetUserId = target.getUser().getId();
        if (!sourceUserId.equals(targetUserId))
            throw new IllegalArgumentException("Transfer only allowed between accounts of the same user");

        if (source.getAccountBalance() < dto.getTransactionAmount())
            throw new IllegalArgumentException(String.format("Insufficient funds. Available: %.2f, Requested: %.2f", source.getAccountBalance(), dto.getTransactionAmount()));

        source.setAccountBalance(source.getAccountBalance() - dto.getTransactionAmount());
        target.setAccountBalance(target.getAccountBalance() + dto.getTransactionAmount());

        accountRepository.save(source);
        accountRepository.save(target);

        Transaction t = Transaction.builder()
                .transactionType("TRANSFER")
                .transactionAmount(dto.getTransactionAmount())
                .sourceAccount(source)
                .targetAccount(target)
                .build();
        return transactionRepository.save(t);
    }

    @Override
    public List<Transaction> getAccountTransactions(Long accountId) {
        accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> getTransactionsByDateRange(LocalDateTime start, LocalDateTime end, String type) {
        return transactionRepository.findByDateRangeAndType(start, end, type);
    }
}
