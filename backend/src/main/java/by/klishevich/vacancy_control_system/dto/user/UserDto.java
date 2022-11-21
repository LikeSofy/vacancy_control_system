package by.klishevich.vacancy_control_system.dto.user;

import by.klishevich.vacancy_control_system.entity.user.RolesEnum;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private RolesEnum role;
}
