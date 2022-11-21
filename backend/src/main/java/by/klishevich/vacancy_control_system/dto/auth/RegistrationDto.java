package by.klishevich.vacancy_control_system.dto.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationDto {
    @NotEmpty()
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9-_]*", message = "Invalid name")
    @Size(max = 30, message = "Name must be shorter than {max}.")
    private String name;

    @NotEmpty()
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9-_]*", message = "Invalid surname")
    @Size(max = 30, message = "Surname must be shorter than {max}.")
    private String surname;

    @Email(message = "Invalid email")
    @Size(max = 60, message = "The email must be shorter than {max}.")
    private String email;

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}", message = "the password must be entered a letter and numbers and be longer than 8 characters and shorter than 16.")
    private String password;
}
