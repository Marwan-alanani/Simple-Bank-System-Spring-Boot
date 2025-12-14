package com.bank.springbootbanksystem.controller;

import com.bank.springbootbanksystem.Dtos.BranchDto;
import com.bank.springbootbanksystem.model.Branch;
import com.bank.springbootbanksystem.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<Branch> createBranch(@Valid @RequestBody BranchDto dto) {
        Branch b = branchService.createBranch(dto);
        return ResponseEntity.ok(b);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchDetails(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranchDetails(id));
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<?> getBranchUsers(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getAllUsersInBranch(id));
    }
}