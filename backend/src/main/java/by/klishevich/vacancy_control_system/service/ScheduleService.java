package by.klishevich.vacancy_control_system.service;

import by.klishevich.vacancy_control_system.dto.shedule.ScheduleDto;
import by.klishevich.vacancy_control_system.dto.shedule.ScheduleCreateEditRequest;
import by.klishevich.vacancy_control_system.entity.Language;
import by.klishevich.vacancy_control_system.entity.schedule.LocalizedScheduleEntity;
import by.klishevich.vacancy_control_system.entity.schedule.ScheduleEntity;
import by.klishevich.vacancy_control_system.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class ScheduleService extends BaseService<ScheduleDto, ScheduleEntity, ScheduleCreateEditRequest, ScheduleCreateEditRequest> {

    public ScheduleService(ScheduleRepository repository) {
        super(repository);
    }

    public ScheduleDto toDto(ScheduleEntity entity) {
        ScheduleDto dto = new ScheduleDto();
        dto.setId(entity.getId());

        for (Language language : entity.getLocalizedData().keySet()) {
            dto.getName().put(language, entity.getLocalizedData().get(language).getName());
        }

        return dto;
    }

    @Override
    public ScheduleEntity toEntity(ScheduleCreateEditRequest request) {
        ScheduleEntity entity = new ScheduleEntity();
        entity.setLocalizedData(new HashMap<>());
        for (Language language : Language.values()) {
            LocalizedScheduleEntity localizedScheduleEntity = new LocalizedScheduleEntity();
            localizedScheduleEntity.setName(request.getName().get(language));
            entity.getLocalizedData().put(language, localizedScheduleEntity);
        }

        return entity;
    }


    @Override
    public ScheduleEntity update(ScheduleEntity scheduleEntity, ScheduleCreateEditRequest scheduleCreateEditRequest) {
        ScheduleEntity entity = toEntity(scheduleCreateEditRequest);
        entity.setId(scheduleEntity.getId());
        return entity;
    }
}
