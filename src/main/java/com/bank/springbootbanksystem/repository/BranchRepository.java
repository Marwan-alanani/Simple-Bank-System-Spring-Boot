package com.bank.springbootbanksystem.repository;

import com.bank.springbootbanksystem.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    Optional<Branch> findBranchByBranchCode(String branchCode);

    List<Branch> findBranchById(long id);

    Optional<Branch> findBranchByBranchAddressContainingIgnoreCase(String address);
}
