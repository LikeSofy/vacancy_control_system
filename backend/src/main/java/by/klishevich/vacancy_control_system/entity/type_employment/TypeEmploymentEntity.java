package by.klishevich.vacancy_control_system.entity.type_employment;

import by.klishevich.vacancy_control_system.entity.Language;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "type_employment")
@NoArgsConstructor
public class TypeEmploymentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "type_employment_id")
    @MapKeyColumn(name = "language")
    private Map<Language, LocalizedTypeEmployment> localizedData;
}
