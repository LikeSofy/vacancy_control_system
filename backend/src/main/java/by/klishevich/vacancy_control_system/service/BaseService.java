package by.klishevich.vacancy_control_system.service;

import by.klishevich.vacancy_control_system.criteria.BaseCriteria;
import by.klishevich.vacancy_control_system.entity.PageDto;
import by.klishevich.vacancy_control_system.exceptions.NotFoundException;
import by.klishevich.vacancy_control_system.repository.CrudRepository;
import by.klishevich.vacancy_control_system.specification.SpecificationAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseService<DTO, ENTITY, EDIT_REQUEST, CREATE_REQUEST> {
    final CrudRepository<ENTITY, Long> repository;

    public PageDto<DTO> findAll(BaseCriteria<ENTITY> criteria) {
        final Page<ENTITY> entities = repository.findAll(new SpecificationAdapter<>(criteria), criteria.toPageable());
        PageDto<DTO> pageDto = new PageDto<>();
        pageDto.setTotal(entities.getNumberOfElements());
        pageDto.setData(entities.map(this::toDto).toList());

        return pageDto;
    }

    public List<DTO> findAllByIds(List<Long> ids) {
        final List<ENTITY> entities = repository.findByIdIn(ids);
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public DTO findById(Long id) {
        final ENTITY entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found"));

        return toDto(entity);
    }

    public DTO create(CREATE_REQUEST request) {
        ENTITY entity = toEntity(request);
        repository.save(entity);

        return toDto(entity);
    }

    public DTO update(Long id, EDIT_REQUEST request) {
        ENTITY entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found"));

        entity = update(entity, request);
        repository.save(entity);

        return toDto(entity);
    }

    public void delete(Long id) {
        final ENTITY entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found"));

        repository.delete(entity);
    }

    public void delete(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    public abstract DTO toDto(ENTITY entity);

    public abstract ENTITY toEntity(CREATE_REQUEST request);

    public abstract ENTITY update(ENTITY entity, EDIT_REQUEST request);
}
