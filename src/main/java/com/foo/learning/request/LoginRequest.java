package com.foo.learning.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @Nonnull
    @NotBlank
    private String email;
    @Nonnull
    @NotBlank
    private String password;
}
