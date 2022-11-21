package by.klishevich.vacancy_control_system.service;

import by.klishevich.vacancy_control_system.dto.interview.InterviewCreateEditRequest;
import by.klishevich.vacancy_control_system.dto.interview.InterviewDto;
import by.klishevich.vacancy_control_system.dto.report.ReportDto;
import by.klishevich.vacancy_control_system.entity.interview.InterviewEntity;
import by.klishevich.vacancy_control_system.entity.report.ReportEntity;
import by.klishevich.vacancy_control_system.entity.user.RolesEnum;
import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import by.klishevich.vacancy_control_system.entity.vacancy.VacancyEntity;
import by.klishevich.vacancy_control_system.exceptions.BadRequestException;
import by.klishevich.vacancy_control_system.exceptions.NotFoundException;
import by.klishevich.vacancy_control_system.repository.InterviewRepository;
import by.klishevich.vacancy_control_system.repository.UserRepository;
import by.klishevich.vacancy_control_system.repository.VacancyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class InterviewService extends BaseService<InterviewDto, InterviewEntity, InterviewCreateEditRequest, InterviewCreateEditRequest> {
    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;

    public InterviewService(
            InterviewRepository repository,
            UserRepository userRepository,
            VacancyRepository vacancyRepository
    ) {
        super(repository);
        this.userRepository = userRepository;
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public InterviewDto toDto(InterviewEntity interviewEntity) {
        InterviewDto dto = new InterviewDto();
        dto.setId(interviewEntity.getId());
        dto.setStart(interviewEntity.getStart());
        Optional.ofNullable(interviewEntity.getUser()).ifPresent(value -> dto.setUserId(value.getId()));
        dto.setInterviewerId(interviewEntity.getInterviewer().getId());
        dto.setVacancyId(interviewEntity.getVacancy().getId());
        Optional.ofNullable(interviewEntity.getReport()).ifPresent(report -> {
            ReportDto reportDto = new ReportDto();
            reportDto.setId(report.getId());
            reportDto.setText(report.getText());
            reportDto.setGrade(report.getGrade());

            dto.setReport(reportDto);
        });

        return dto;
    }

    @Override
    public InterviewEntity toEntity(InterviewCreateEditRequest request) {
        log.info(request.toString());

        UserEntity interviewer = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!interviewer.getRole().equals(RolesEnum.ROLE_INTERVIEWER)) {
            Long id = request.getInterviewerId()
                    .orElseThrow(() -> new BadRequestException("User can't be empty"));

            interviewer = userRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("User not found"));
        }
        else {
            interviewer = userRepository.findById(interviewer.getId())
                    .orElseThrow(() -> new BadRequestException("User not found"));
        }

        VacancyEntity vacancy = vacancyRepository.findById(request.getVacancyId())
                .orElseThrow(() -> new BadRequestException("Vacancy not found"));

        InterviewEntity interviewEntity = new InterviewEntity();
        interviewEntity.setStart(request.getStart());
        interviewEntity.setInterviewer(interviewer);
        interviewEntity.setVacancy(vacancy);

        request.getReport().ifPresent(report -> {
            ReportEntity reportEntity = new ReportEntity();
            reportEntity.setText(report.getText());
            reportEntity.setGrade(report.getGrade());

            interviewEntity.setReport(reportEntity);
        });

        request.getUserId().map(id -> userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"))).ifPresent(interviewEntity::setUser);

        return interviewEntity;
    }

    public InterviewDto subscribe(Long id, UserEntity user) {
        InterviewEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found"));

        entity.setUser(user);
        repository.save(entity);

        return toDto(entity);
    }

    public InterviewDto unsubscribe(Long id, UserEntity user) {
        InterviewEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found"));

        if (!entity.getUser().getId().equals(user.getId())){
            throw new NotFoundException("Not found");
        }

        if (entity.getReport() != null) {
            throw new NotFoundException("Not found");
        }

        entity.setUser(null);
        repository.save(entity);

        return toDto(entity);
    }

    @Override
    public InterviewEntity update(InterviewEntity interviewEntity, InterviewCreateEditRequest request) {
        InterviewEntity newInterviewEntity = toEntity(request);
        newInterviewEntity.setId(interviewEntity.getId());
        Optional.ofNullable(interviewEntity.getReport()).map(ReportEntity::getId)
                .ifPresent(value -> newInterviewEntity.getReport().setId(value));

        return newInterviewEntity;
    }
}
