package by.klishevich.vacancy_control_system.dto.type_employment;

import by.klishevich.vacancy_control_system.entity.Language;
import lombok.Data;

import java.util.Map;

@Data
public class TypeEmploymentCreateEditRequest {
    private Map<Language, String> name;
}
