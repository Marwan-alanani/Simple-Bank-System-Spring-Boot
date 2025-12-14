package com.bank.springbootbanksystem.service.IService;

import com.bank.springbootbanksystem.Dtos.AccountDto;
import com.bank.springbootbanksystem.model.Account;

import java.util.List;

public interface IAccountService {
    Account createAccount(AccountDto accountDto);
    Account updateAccountBalance(Long accountId, Double amount);
    Account getAccountDetails(Long accountId);

}
