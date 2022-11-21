package by.klishevich.vacancy_control_system.entity.schedule;

import by.klishevich.vacancy_control_system.entity.BaseEntity;
import by.klishevich.vacancy_control_system.entity.Language;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "schedule")
@NoArgsConstructor
public class ScheduleEntity implements BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "schedule_id")
    @MapKeyColumn(name = "language")
    private Map<Language, LocalizedScheduleEntity> localizedData;
}
