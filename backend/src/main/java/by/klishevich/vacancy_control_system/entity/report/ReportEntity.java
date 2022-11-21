package by.klishevich.vacancy_control_system.entity.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "report")
@NoArgsConstructor
public class ReportEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text", length = 5000)
    private String text;

    @Column(name = "grade", length = 100)
    private String grade;
}
