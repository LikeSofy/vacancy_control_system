package by.klishevich.vacancy_control_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CrudRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    List<T> findByIdIn(List<ID> ids);
}
