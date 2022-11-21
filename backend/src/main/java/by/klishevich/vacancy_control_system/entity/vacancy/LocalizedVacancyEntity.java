package by.klishevich.vacancy_control_system.entity.vacancy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "localized_vacancy")
public class LocalizedVacancyEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;
}
