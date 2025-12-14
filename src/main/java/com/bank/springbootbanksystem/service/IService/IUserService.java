package com.bank.springbootbanksystem.service.IService;

import com.bank.springbootbanksystem.Dtos.UserDto;
import com.bank.springbootbanksystem.model.Account;
import com.bank.springbootbanksystem.model.Branch;
import com.bank.springbootbanksystem.model.User;

import java.util.List;

public interface IUserService {
    User createUser(UserDto userDto);
    Branch findNearestBranch(String address);
    User getUserDetails(Long userId);
    User linkUserToBranch(Long userId, Long branchId);
    List<Account> getUserAccounts(Long userId);
}
