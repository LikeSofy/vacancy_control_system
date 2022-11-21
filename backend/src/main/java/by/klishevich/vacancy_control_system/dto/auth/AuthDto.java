package by.klishevich.vacancy_control_system.dto.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class AuthDto {
    @NotEmpty(message="Email can't be empty")
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}", message = "Password must be entered a letter and numbers and be longer than 8 characters and shorter than 16.")
    private String password;
}
