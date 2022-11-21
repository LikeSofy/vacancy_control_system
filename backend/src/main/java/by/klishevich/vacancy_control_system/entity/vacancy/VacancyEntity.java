package by.klishevich.vacancy_control_system.entity.vacancy;

import by.klishevich.vacancy_control_system.entity.BaseEntity;
import by.klishevich.vacancy_control_system.entity.Language;
import by.klishevich.vacancy_control_system.entity.schedule.ScheduleEntity;
import by.klishevich.vacancy_control_system.entity.type_employment.TypeEmploymentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "vacancy")
@NoArgsConstructor
public class VacancyEntity implements BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "vacancy_id")
    @MapKeyColumn(name = "language")
    private Map<Language, LocalizedVacancyEntity> localizedData = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity schedule;

    @ManyToOne
    @JoinColumn(name = "type_employment_id")
    private TypeEmploymentEntity typeEmploymentEntity;

    @Column(name = "salary")
    private BigDecimal salary;
}
