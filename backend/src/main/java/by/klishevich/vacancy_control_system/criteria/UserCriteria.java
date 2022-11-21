package by.klishevich.vacancy_control_system.criteria;

import by.klishevich.vacancy_control_system.entity.user.RolesEnum;
import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Data
public class UserCriteria extends BaseCriteria<UserEntity> {
    private Optional<RolesEnum> role = Optional.empty();

    @Override
    public List<Predicate> toPredicates(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new LinkedList<>();
        role.ifPresent(value -> predicates.add(criteriaBuilder.equal(root.get("role"), value)));

        return predicates;
    }
}
