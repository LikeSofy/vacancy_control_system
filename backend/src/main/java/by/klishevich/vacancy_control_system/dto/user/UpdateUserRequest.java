package by.klishevich.vacancy_control_system.dto.user;

import by.klishevich.vacancy_control_system.entity.user.RolesEnum;
import lombok.Data;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.*;

@Data
public class UpdateUserRequest {
    @NotEmpty()
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9-_]*", message = "Invalid name")
    @Size(max = 30, message = "Name must be shorter than {max}.")
    private String name;
    @NotEmpty()
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9-_]*", message = "Invalid surname")
    @Size(max = 30, message = "Surname must be shorter than {max}.")
    private String surname;
    @NotEmpty()
    @Email(message = "Invalid email")
    @Size(max = 60, message = "The email must be shorter than {max}.")
    private String email;
    @NotNull()
    private RolesEnum role;
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}", message = "the password must be entered a letter and numbers and be longer than 8 characters and shorter than 16.")
    private JsonNullable<String> password = JsonNullable.undefined();
}
