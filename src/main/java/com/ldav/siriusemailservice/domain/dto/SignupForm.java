package com.ldav.siriusemailservice.domain.dto;

import com.ldav.siriusemailservice.validation.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {
    @NotNull(message = "Username must be present")
    @NotBlank(message = "Username must contain something other than whitespace characters")
    @Email(message = "Username must be an email address")
    private String username;
    @NotNull(message = "Password must be present")
    @NotBlank(message = "Password must contain something other than whitespace characters")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
    message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character, and be at least 8 characters long")
    private String password;
}
