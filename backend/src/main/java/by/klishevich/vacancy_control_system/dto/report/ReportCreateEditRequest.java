package by.klishevich.vacancy_control_system.dto.report;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ReportCreateEditRequest {
    @NotEmpty(message = "Report text can't be empty")
    private String text;
    @NotEmpty(message = "Grade can't be empty")
    private String grade;
}
