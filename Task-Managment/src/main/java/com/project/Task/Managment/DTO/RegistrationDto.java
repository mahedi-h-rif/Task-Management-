package com.project.Task.Managment.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;


@Getter
@Setter
@AllArgsConstructor
public class RegistrationDto {

    @NotNull(message = "Username shouldn't be null.")
    private String userName;
    @Email(message = "Invalid email address.")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)" +
            "(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least one lowercase letter," +
            "one uppercase letter, one digit, and one special character")
    private String password;



}
