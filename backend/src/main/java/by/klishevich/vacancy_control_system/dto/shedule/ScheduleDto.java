package by.klishevich.vacancy_control_system.dto.shedule;

import by.klishevich.vacancy_control_system.entity.Language;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ScheduleDto {
    private Long id;
    private Map<Language, String> name = new HashMap<>();
}
