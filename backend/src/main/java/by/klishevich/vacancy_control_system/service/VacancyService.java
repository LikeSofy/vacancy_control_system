package by.klishevich.vacancy_control_system.service;

import by.klishevich.vacancy_control_system.dto.vacancy.VacancyCreateEditRequest;
import by.klishevich.vacancy_control_system.dto.vacancy.VacancyDto;
import by.klishevich.vacancy_control_system.entity.Language;
import by.klishevich.vacancy_control_system.entity.schedule.ScheduleEntity;
import by.klishevich.vacancy_control_system.entity.type_employment.TypeEmploymentEntity;
import by.klishevich.vacancy_control_system.entity.vacancy.LocalizedVacancyEntity;
import by.klishevich.vacancy_control_system.entity.vacancy.VacancyEntity;
import by.klishevich.vacancy_control_system.exceptions.BadRequestException;
import by.klishevich.vacancy_control_system.repository.ScheduleRepository;
import by.klishevich.vacancy_control_system.repository.TypeEmploymentRepository;
import by.klishevich.vacancy_control_system.repository.VacancyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VacancyService extends BaseService<VacancyDto, VacancyEntity, VacancyCreateEditRequest, VacancyCreateEditRequest> {
    private final ScheduleRepository scheduleRepository;
    private final TypeEmploymentRepository typeEmploymentRepository;

    public VacancyService(VacancyRepository repository, ScheduleRepository scheduleRepository, TypeEmploymentRepository typeEmploymentRepository) {
        super(repository);
        this.scheduleRepository = scheduleRepository;
        this.typeEmploymentRepository = typeEmploymentRepository;
    }

    @Override
    public VacancyDto toDto(VacancyEntity vacancyEntity) {
        VacancyDto vacancyDto = new VacancyDto();
        vacancyDto.setId(vacancyEntity.getId());
        vacancyDto.setScheduleId(vacancyEntity.getSchedule().getId());
        vacancyDto.setTypeEmploymentId(vacancyEntity.getTypeEmploymentEntity().getId());
        vacancyDto.setSalary(vacancyEntity.getSalary());

        for (Language language : vacancyEntity.getLocalizedData().keySet()) {
            vacancyDto.getName().put(language, vacancyEntity.getLocalizedData().get(language).getName());
            vacancyDto.getDescription().put(language, vacancyEntity.getLocalizedData().get(language).getDescription());
        }

        return vacancyDto;
    }

    @Override
    public VacancyEntity toEntity(VacancyCreateEditRequest request) {
        ScheduleEntity schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new BadRequestException("Schedule not found"));

        TypeEmploymentEntity typeEmploymentEntity = typeEmploymentRepository.findById(request.getTypeEmploymentId())
                .orElseThrow(() -> new BadRequestException("Type employment not found"));

        VacancyEntity entity = new VacancyEntity();
        entity.setSchedule(schedule);
        entity.setTypeEmploymentEntity(typeEmploymentEntity);
        entity.setSalary(request.getSalary());

        for (Language language : Language.values()) {
            LocalizedVacancyEntity localizedVacancy = new LocalizedVacancyEntity();
            localizedVacancy.setName(request.getName().get(language));
            localizedVacancy.setDescription(request.getDescription().get(language));
            entity.getLocalizedData().put(language, localizedVacancy);
        }

        return entity;
    }

    @Override
    public VacancyEntity update(VacancyEntity vacancyEntity, VacancyCreateEditRequest request) {
        VacancyEntity entity = toEntity(request);
        entity.setId(vacancyEntity.getId());

        return entity;
    }
}
