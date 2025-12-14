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
@Table(name = "accounts")
@Builder
public class Account {
    @Id
    @GeneratedValue
    private long Id;
    @Column(nullable = false, unique = true)
    private String accountNumber;
    @Column(nullable = false)
    private double accountBalance = 0.0;
    @Column(nullable = false)
    private String accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "sourceAccount", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction>  outgoingTransaction =  new ArrayList<>();

    @OneToMany(mappedBy = "targetAccount", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction>  incomingTransaction =  new ArrayList<>();

}
