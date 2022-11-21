package by.klishevich.vacancy_control_system.service;

import by.klishevich.vacancy_control_system.dto.type_employment.TypeEmploymentCreateEditRequest;
import by.klishevich.vacancy_control_system.dto.type_employment.TypeEmploymentDto;
import by.klishevich.vacancy_control_system.entity.Language;
import by.klishevich.vacancy_control_system.entity.type_employment.LocalizedTypeEmployment;
import by.klishevich.vacancy_control_system.entity.type_employment.TypeEmploymentEntity;
import by.klishevich.vacancy_control_system.repository.TypeEmploymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class TypeEmploymentService extends BaseService<TypeEmploymentDto, TypeEmploymentEntity, TypeEmploymentCreateEditRequest, TypeEmploymentCreateEditRequest> {

    public TypeEmploymentService(TypeEmploymentRepository repository) {
        super(repository);
    }

    @Override
    public TypeEmploymentDto toDto(TypeEmploymentEntity entity) {
        TypeEmploymentDto dto = new TypeEmploymentDto();
        dto.setId(entity.getId());

        for (Language language : entity.getLocalizedData().keySet()) {
            dto.getName().put(language, entity.getLocalizedData().get(language).getName());
        }

        return dto;
    }

    @Override
    public TypeEmploymentEntity toEntity(TypeEmploymentCreateEditRequest request) {
        TypeEmploymentEntity entity = new TypeEmploymentEntity();
        entity.setLocalizedData(new HashMap<>());
        for (Language language : Language.values()) {
            LocalizedTypeEmployment localizedEntity = new LocalizedTypeEmployment();
            localizedEntity.setName(request.getName().get(language));
            entity.getLocalizedData().put(language, localizedEntity);
        }

        return entity;
    }

    @Override
    public TypeEmploymentEntity update(TypeEmploymentEntity typeEmploymentEntity, TypeEmploymentCreateEditRequest typeEmploymentCreateEditRequest) {
        TypeEmploymentEntity entity = toEntity(typeEmploymentCreateEditRequest);
        entity.setId(typeEmploymentEntity.getId());
        return entity;
    }

}
