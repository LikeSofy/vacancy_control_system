package by.klishevich.vacancy_control_system.dto.vacancy;

import by.klishevich.vacancy_control_system.entity.Language;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class VacancyDto {
    private Long id;
    private Map<Language, String> name = new HashMap<>();
    private Map<Language, String> description = new HashMap<>();
    private Long scheduleId;
    private Long typeEmploymentId;
    private BigDecimal salary;
}
