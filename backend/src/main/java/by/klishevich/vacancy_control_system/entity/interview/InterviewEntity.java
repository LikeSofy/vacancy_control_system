package by.klishevich.vacancy_control_system.entity.interview;

import by.klishevich.vacancy_control_system.entity.report.ReportEntity;
import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import by.klishevich.vacancy_control_system.entity.vacancy.VacancyEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "interview")
@NoArgsConstructor
public class InterviewEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_interview", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    private UserEntity interviewer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private VacancyEntity vacancy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private ReportEntity report;
}
