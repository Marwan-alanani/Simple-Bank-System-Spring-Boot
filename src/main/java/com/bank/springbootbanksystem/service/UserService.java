package com.bank.springbootbanksystem.service;

import com.bank.springbootbanksystem.Dtos.UserDto;
import com.bank.springbootbanksystem.model.Account;
import com.bank.springbootbanksystem.model.Branch;
import com.bank.springbootbanksystem.model.User;
import com.bank.springbootbanksystem.repository.AccountRepository;
import com.bank.springbootbanksystem.repository.BranchRepository;
import com.bank.springbootbanksystem.repository.UserRepository;
import com.bank.springbootbanksystem.service.IService.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.springbootbanksystem.service.BranchService;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final AccountRepository accountRepository;
    private final BranchService branchService;


    @Override
    public User createUser(UserDto userDto) {
        if(userRepository.existsByUserEmail(userDto.getUserEmail())) {
            throw new EntityNotFoundException("Email already in use");
        }
        Branch branch = findNearestBranch(userDto.getAddress());
        User user = User.builder()
                .userName(userDto.getUserName())
                .userAddress(userDto.getAddress())
                .userEmail(userDto.getUserEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .branch(null)
                .build();
        return userRepository.save(user);
    }

    @Override
    public Branch findNearestBranch(String address) {
        Optional<Branch> branches = branchRepository.findBranchByBranchAddressContainingIgnoreCase(address);
        if(branches.isEmpty()) {
            throw new EntityNotFoundException("No branches available");
        }
        return branches.get();
    }

    @Override
    public User getUserDetails(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User linkUserToBranch(Long userId, Long branchId) {
        User user = getUserDetails(userId);
        Branch branch = branchService.getBranchDetails(branchId);
        user.setBranch(branch);
        return userRepository.save(user);
    }

    @Override
    public List<Account> getUserAccounts(Long userId) {
        getUserDetails(userId);
        return accountRepository.findAccountsByUser_Id(userId);
    }
}
