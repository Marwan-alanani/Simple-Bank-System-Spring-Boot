package com.bank.springbootbanksystem.service;

import com.bank.springbootbanksystem.Dtos.AccountDto;
import com.bank.springbootbanksystem.Dtos.BranchDto;
import com.bank.springbootbanksystem.model.Account;
import com.bank.springbootbanksystem.model.Branch;
import com.bank.springbootbanksystem.model.User;
import com.bank.springbootbanksystem.repository.AccountRepository;
import com.bank.springbootbanksystem.service.IService.IAccountService;
import com.bank.springbootbanksystem.service.IService.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    public Account createAccount(AccountDto accountDto) {
        List<Account> accounts = accountRepository.findAccountsByUser_Id(accountDto.getUserId());
        User u = userService.getUserDetails(accountDto.getUserId());
        if (accounts.isEmpty()) {
            Account account = Account.builder()
                    .accountNumber(accountDto.getAccountNumber())
                    .accountBalance(accountDto.getAccountBalance())
                    .accountType(accountDto.getAccountType())
                    .user(u)
                    .build();
            return accountRepository.save(account);
        }else  {
            throw  new RuntimeException("Account already exists");
        }
    }

    @Override
    public Account updateAccountBalance(Long accountId, Double amount) {
        Optional<Account> accounts = accountRepository.findById(accountId);
        if (accounts.isPresent()) {
            Account account = accounts.get();
            account.setAccountBalance(account.getAccountBalance() + amount);
            return accountRepository.save(account);
        }else  {
            throw  new RuntimeException("Account does not exist");
        }
    }

    @Override
    public Account getAccountDetails(Long accountId) {
        Optional<Account> accounts = accountRepository.findById(accountId);
        if (accounts.isPresent()) {
            Account account = accounts.get();
            return account;
        }else   {
            throw  new RuntimeException("Account does not exist");
        }
    }
}
