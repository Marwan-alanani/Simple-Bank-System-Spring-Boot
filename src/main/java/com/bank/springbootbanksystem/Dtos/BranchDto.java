package com.bank.springbootbanksystem.Dtos;

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

public class BranchDto {
    @NotBlank(message = "vjhjhv")
    private String branchName;

    @NotBlank(message = "Branch code is required.")
    @Pattern(regexp = "^[A-Za-z]{3}\\d{3}$", message = "Branch code must be 3 letters followed by 3 digits (e.g., ABC123).")
    private String branchCode;

    @NotBlank(message = "vgig")
    private String branchAddress;
}
