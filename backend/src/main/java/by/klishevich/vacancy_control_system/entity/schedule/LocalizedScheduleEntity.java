package by.klishevich.vacancy_control_system.entity.schedule;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "localized_schedules")
@NoArgsConstructor
public class LocalizedScheduleEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;
}
