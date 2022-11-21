package by.klishevich.vacancy_control_system.criteria;

import by.klishevich.vacancy_control_system.entity.vacancy.VacancyEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Data
public class VacancyCriteria extends BaseCriteria<VacancyEntity> {
    private List<Long> schedulesIdIn = new ArrayList<>();
    private List<Long> typeEmploymentIdIn = new ArrayList<>();

    @Override
    public List<Predicate> toPredicates(Root<VacancyEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        log.info(typeEmploymentIdIn.toString());
        List<Predicate> predicates = new LinkedList<>();
        if (!schedulesIdIn.isEmpty()){
            CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("schedule").get("id"));
            schedulesIdIn.forEach(id -> in.value(id));
            predicates.add(in);
        }
        if (!typeEmploymentIdIn.isEmpty()){
            CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("typeEmploymentEntity").get("id"));
            typeEmploymentIdIn.forEach(id -> in.value(id));
            predicates.add(in);
        }

        return predicates;
    }
}
