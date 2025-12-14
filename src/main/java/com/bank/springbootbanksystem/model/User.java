package com.bank.springbootbanksystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "users")
@Builder

public class User {
    @Id
    @GeneratedValue
    private Long Id;
    @Column(nullable = false)
    private String userName;
    private String userAddress;
    @Column(nullable = false, unique = true)
    private String userEmail;

    private String phoneNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id",referencedColumnName = "id", nullable = true)
    private Branch branch;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonIgnore
    private List<Account> Account = new ArrayList<>();


}
