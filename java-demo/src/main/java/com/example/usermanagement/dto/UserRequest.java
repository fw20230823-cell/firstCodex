package com.example.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(@NotBlank String name, @NotBlank String email) { }
