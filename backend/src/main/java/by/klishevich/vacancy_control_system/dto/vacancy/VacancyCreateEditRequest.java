package by.klishevich.vacancy_control_system.dto.vacancy;

import by.klishevich.vacancy_control_system.entity.Language;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class VacancyCreateEditRequest {
    private Map<Language, String> name;
    private Map<Language, String> description;
    private Long scheduleId;
    private Long typeEmploymentId;
    private BigDecimal salary;
}
