package by.klishevich.vacancy_control_system.dto.interview;

import by.klishevich.vacancy_control_system.dto.report.ReportDto;
import lombok.Data;

import java.util.Date;

@Data
public class InterviewDto {
    private Long id;
    private Date start;
    private Long interviewerId;
    private Long userId;
    private Long vacancyId;
    private ReportDto report;
}
