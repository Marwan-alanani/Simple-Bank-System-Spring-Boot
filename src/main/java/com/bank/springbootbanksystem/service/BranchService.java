package com.bank.springbootbanksystem.service;

import com.bank.springbootbanksystem.Dtos.BranchDto;
import com.bank.springbootbanksystem.model.Branch;
import com.bank.springbootbanksystem.model.User;
import com.bank.springbootbanksystem.repository.BranchRepository;
import com.bank.springbootbanksystem.service.IService.IAccountService;
import com.bank.springbootbanksystem.service.IService.IBranchService;
import com.bank.springbootbanksystem.service.IService.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor

public class BranchService implements IBranchService {

    @Autowired
    private final BranchRepository branchRepository;


    @Override
    public Branch createBranch(BranchDto branchDto) {
        branchRepository.findBranchByBranchCode(branchDto.getBranchCode());

        Branch branch = Branch.builder()
                .branchCode(branchDto.getBranchCode())
                .branchName(branchDto.getBranchName())
                .branchAddress(branchDto.getBranchAddress())
                .build();
        return branchRepository.save(branch);
    }

    @Override
    public Branch getBranchDetails(Long branchId) {
        var x = branchRepository.findById(branchId);
        if (x.isEmpty()) {
            throw new EntityNotFoundException("Branch not found");
        }
        return x.get();
    }

    @Override
    public List<User> getAllUsersInBranch(Long branchId) {
        Branch branch = getBranchDetails(branchId);
        return branch.getUser();
    }
}
