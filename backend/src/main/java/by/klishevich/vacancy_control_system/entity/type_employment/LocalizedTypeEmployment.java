package by.klishevich.vacancy_control_system.entity.type_employment;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "localized_type_employment")
@NoArgsConstructor
public class LocalizedTypeEmployment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;
}