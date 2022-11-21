package by.klishevich.vacancy_control_system.dto.interview;

import by.klishevich.vacancy_control_system.dto.report.ReportCreateEditRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Data
public class InterviewCreateEditRequest {
    @NotNull(message = "Start time can't be empty")
    private Date start;
    private Optional<Long> interviewerId = Optional.empty();
    private Optional<Long> userId = Optional.empty();
    @NotNull(message = "Vacancy can't be empty")
    private Long vacancyId;
    private Optional<ReportCreateEditRequest> report = Optional.empty();
}
