package com.bank.springbootbanksystem.service.IService;

import com.bank.springbootbanksystem.Dtos.BranchDto;
import com.bank.springbootbanksystem.model.Branch;
import com.bank.springbootbanksystem.model.User;

import java.util.List;

public interface IBranchService {
    Branch createBranch(BranchDto branchDto);
    Branch getBranchDetails(Long branchId);
    List<User> getAllUsersInBranch(Long branchId);
}
