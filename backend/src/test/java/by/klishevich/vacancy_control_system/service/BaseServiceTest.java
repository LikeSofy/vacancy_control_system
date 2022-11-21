package by.klishevich.vacancy_control_system.service;

import by.klishevich.vacancy_control_system.criteria.BaseCriteria;
import by.klishevich.vacancy_control_system.entity.PageDto;
import by.klishevich.vacancy_control_system.exceptions.NotFoundException;
import by.klishevich.vacancy_control_system.repository.CrudRepository;
import by.klishevich.vacancy_control_system.specification.SpecificationAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BaseServiceTest {
    BaseService<Object, Object, Object, Object> baseService;

    CrudRepository<Object, Long> repository;

    @BeforeEach
    void setUp() {
        this.repository = Mockito.mock(CrudRepository.class);
        this.baseService = Mockito.mock(BaseService.class,
                Mockito
                        .withSettings()
                        .useConstructor(this.repository)
                        .defaultAnswer(Mockito.CALLS_REAL_METHODS)
        );
    }

    @Test
    void findAll__positive__returnPageDto() {
        BaseCriteria<Object> mockCriteria = (BaseCriteria<Object>) Mockito.mock(BaseCriteria.class);
        Pageable expectedPageable = Mockito.mock(Pageable.class);
        Mockito.doReturn(expectedPageable).when(mockCriteria).toPageable();
        SpecificationAdapter<Object> expectedSpecificationAdapter = new SpecificationAdapter<>(mockCriteria);
        Page<Object> mockPage = (Page<Object>) Mockito.mock(Page.class);
        Mockito.doReturn(mockPage).when(repository).findAll(expectedSpecificationAdapter, expectedPageable);
        Mockito.doReturn(10).when(mockPage).getNumberOfElements();
        Page<Object> mappedMockPage = (Page<Object>) Mockito.mock(Page.class);
        Mockito.doReturn(mappedMockPage).when(mockPage).map(Mockito.any());
        List<Object> mockList = (List<Object>) Mockito.mock(List.class);
        Mockito.doReturn(mockList).when(mappedMockPage).toList();
        PageDto<Object> expected = new PageDto<>();
        expected.setTotal(10);
        expected.setData(mockList);

        PageDto<Object> actual = baseService.findAll(mockCriteria);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByIds__positive__returnPageDto() {
        List<Long> mockIdsList = Mockito.mock(List.class);
        List<Object> mockResultList = Mockito.mock(List.class);
        Mockito.doReturn(mockResultList).when(repository).findByIdIn(mockIdsList);
        Stream<Object> mockStream = Mockito.mock(Stream.class);
        Mockito.doReturn(mockStream).when(mockResultList).stream();
        Stream<Object> mockMappedStream = Mockito.mock(Stream.class);
        Mockito.doReturn(mockMappedStream).when(mockStream).map(Mockito.any());
        List<Object> expected = Mockito.mock(List.class);
        Mockito.doReturn(expected).when(mockMappedStream).collect(Mockito.any());

        List<Object> actual = baseService.findAllByIds(mockIdsList);

        assertEquals(expected, actual);
    }

    @Test
    void findById__positive__returnDto() {
        Long mockId = 10L;
        Object mockResult = Mockito.mock(Object.class);
        Optional optional = Optional.of(mockResult);
        Mockito.doReturn(optional).when(repository).findById(mockId);
        Object expected = Mockito.mock(Object.class);
        Mockito.doReturn(expected).when(baseService).toDto(mockResult);

        Object actual = baseService.findById(mockId);

        assertEquals(expected, actual);
    }

    @Test
    void findById__negative__throwException() {
        Long mockId = 10L;
        Optional optional = Optional.empty();
        Mockito.doReturn(optional).when(repository).findById(mockId);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            baseService.findById(mockId);
        });

        String expected = "Not found";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void create__positive__returnDto() {
        Object mockRequest = Mockito.mock(Object.class);
        Object mockEntity = Mockito.mock(Object.class);
        Mockito.doReturn(mockEntity).when(baseService).toEntity(mockRequest);
        Object expected = Mockito.mock(Object.class);
        Mockito.doReturn(expected).when(baseService).toDto(mockEntity);

        Object actual = baseService.create(mockRequest);

        Mockito.verify(repository, Mockito.times(1)).save(mockEntity);
        assertEquals(expected, actual);
    }

    @Test
    void update__positive__returnDto() {
        Long mockId = 10L;
        Object mockRequest = Mockito.mock(Object.class);
        Object mockResult = Mockito.mock(Object.class);
        Optional optional = Optional.of(mockResult);
        Mockito.doReturn(optional).when(repository).findById(mockId);
        Object mockEntity = Mockito.mock(Object.class);
        Mockito.doReturn(mockEntity).when(baseService).update(mockResult, mockRequest);
        Object expected = Mockito.mock(Object.class);
        Mockito.doReturn(expected).when(baseService).toDto(mockEntity);

        Object actual = baseService.update(mockId, mockRequest);

        Mockito.verify(repository, Mockito.times(1)).save(mockEntity);
        assertEquals(expected, actual);
    }

    @Test
    void update__idNotInRepository__throwException() {
        Long mockId = 10L;
        Object mockRequest = Mockito.mock(Object.class);
        Optional optional = Optional.empty();
        Mockito.doReturn(optional).when(repository).findById(mockId);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            baseService.update(mockId, mockRequest);
        });

        String expected = "Not found";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void delete__positive__sendIdToRepository() {
        Long mockId = 10L;
        Object mockResult = Mockito.mock(Object.class);
        Optional optional = Optional.of(mockResult);
        Mockito.doReturn(optional).when(repository).findById(mockId);

        baseService.delete(mockId);

        Mockito.verify(repository, Mockito.times(1)).delete(mockResult);
    }

    @Test
    void delete__idNotInRepository__throwException() {
        Long mockId = 10L;
        Optional optional = Optional.empty();
        Mockito.doReturn(optional).when(repository).findById(mockId);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            baseService.delete(mockId);
        });

        String expected = "Not found";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void deleteMany__positive__sendIdToRepository() {
        List<Long> mockIdsList = Mockito.mock(List.class);

        baseService.delete(mockIdsList);

        Mockito.verify(repository, Mockito.times(1)).deleteAllById(mockIdsList);
    }
}