package com.bank.springbootbanksystem.repository;

import com.bank.springbootbanksystem.model.Transaction;
import com.bank.springbootbanksystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>  {

    @Query("SELECT t FROM Transaction t WHERE t.sourceAccount.Id\n" +
            "= :accountId OR t.targetAccount.Id = :accountId ORDER BY t.timestamp DESC")
    List<Transaction> findByAccountId(@Param("accountId")Long accountId);

    List<Transaction> findBySourceAccount(Account sourceAccount);

    List<Transaction> findByTargetAccount(Account targetAccount);

    @Query("select t from Transaction t where t.timestamp between :start and :end and (:transactionType is null or t.transactionType = :transactionType) order by t.timestamp DESC ")
    List<Transaction> findByDateRangeAndType( @Param("start") LocalDateTime startDate, @Param("end") LocalDateTime endDate, @Param("transactionType") String
            transactionType);


}
