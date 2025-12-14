package com.bank.springbootbanksystem.controller;

import com.bank.springbootbanksystem.Dtos.UserDto;
import com.bank.springbootbanksystem.model.User;
import com.bank.springbootbanksystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto dto) {
        User u = userService.createUser(dto);
        return ResponseEntity.status(201).body(u);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserDetails(id));
    }

    @PutMapping("/{id}/branch")
    public ResponseEntity<User> linkUserToBranch(@PathVariable Long id, @RequestParam Long branchId) {
        return ResponseEntity.ok(userService.linkUserToBranch(id, branchId));
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<?> getUserAccounts(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserAccounts(id));
    }
}