package com.bank.springbootbanksystem.repository;

import com.bank.springbootbanksystem.model.Account;
import com.bank.springbootbanksystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAccountsByUser(User user);
    List<Account> findAccountsByUser_Id(Long userId);

    Optional<Account> findByAccountNumber(String account_number);

}
