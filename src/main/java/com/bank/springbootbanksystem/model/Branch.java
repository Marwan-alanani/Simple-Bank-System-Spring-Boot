package com.bank.springbootbanksystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branches")
@Builder

public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String branchName;
    @Column(nullable = false, unique = true)
    private String branchCode;
    private String branchAddress;


    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonManagedReference
    private List<User> User = new ArrayList<>();
}
