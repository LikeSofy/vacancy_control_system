package by.klishevich.vacancy_control_system.criteria;

import by.klishevich.vacancy_control_system.entity.interview.InterviewEntity;
import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Data
public class InterviewCriteria extends BaseCriteria<InterviewEntity> {
    private Optional<Long> vacancyId = Optional.empty();
    private Optional<Boolean> me = Optional.empty();
    private Optional<Boolean> busyByUser = Optional.empty();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Optional<Date> startFrom = Optional.empty();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Optional<Date> startTo = Optional.empty();

    @Override
    public List<Predicate> toPredicates(Root<InterviewEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new LinkedList<>();
        vacancyId.ifPresent(id -> predicates.add(criteriaBuilder.equal(root.get("vacancy").get("id"), id)));
        me.ifPresent(value -> {
            if (!value)
                return;

            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (user == null || user.getId() == null)
                return;

            Predicate predicate1 = criteriaBuilder.equal(root.get("interviewer").get("id"), user.getId());
            Predicate predicate2 = criteriaBuilder.equal(root.get("user").get("id"), user.getId());

            predicates.add(criteriaBuilder.or(predicate1, predicate2));
        });
        busyByUser.ifPresent(value -> {
            if (value)
                predicates.add(criteriaBuilder.isNotNull(root.get("user")));
            else
                predicates.add(criteriaBuilder.isNull(root.get("user")));
        });

        log.info(startFrom.toString());
        log.info(startTo.toString());
        startFrom.ifPresent(date -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("start"), date)));
        startTo.ifPresent(date -> predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("start"), date)));

        return predicates;
    }
}
