package com.bank.springbootbanksystem.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    @NotBlank(message = "Name is required.")
    private String userName;

    private String userEmail;

    @NotBlank(message = "Email is required.")
//    @Email(message = "Email must be valid.")
    private String address;

    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Phone number must be in the format XXX-XXX-XXXX.")
    private String phoneNumber;


    private String Account;
}
