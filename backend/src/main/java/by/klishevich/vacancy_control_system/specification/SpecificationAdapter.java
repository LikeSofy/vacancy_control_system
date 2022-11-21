package by.klishevich.vacancy_control_system.specification;

import by.klishevich.vacancy_control_system.criteria.BaseCriteria;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Adapter pattern between {@link BaseCriteria} and {@link Specification}
 * <a href="https://refactoring.guru/ru/design-patterns/adapter/java/example#lang-features">more info about pattern</a>
 * @param <T> - entity class
 */
@AllArgsConstructor
@EqualsAndHashCode
public class SpecificationAdapter<T> implements Specification<T>{

    private BaseCriteria<T> criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = criteria.toPredicates(root, query, criteriaBuilder);

        if (predicates.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        Predicate predicate = predicates.get(0);

        for (int i = 1; i < predicates.size(); i++) {
            predicate = criteriaBuilder.and(predicate, predicates.get(i));
        }

        return predicate;
    }
}
