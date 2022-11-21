package by.klishevich.vacancy_control_system.criteria;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * Superclass that implements a template method pattern for filtering entities result
 * <a href="https://refactoring.guru/ru/design-patterns/template-method/java/example#lang-features">more info about pattern</a>
 *
 * @param <T> - entity class
 */
@Data
public abstract class BaseCriteria<T> {
    private Optional<String> sortBy = Optional.empty();
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private Integer pageNumber = 1;
    private Integer pageSize = 25;

    public List<Predicate> toPredicates(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return List.of(criteriaBuilder.conjunction());
    }


    public Pageable toPageable() {
        Sort sort = Sort.unsorted();
        sortBy.ifPresent(value -> Sort.by(sortDirection, value));

        return PageRequest.of(pageNumber - 1, pageSize, sort);
    }
}
