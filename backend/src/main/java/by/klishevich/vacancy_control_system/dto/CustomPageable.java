package by.klishevich.vacancy_control_system.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomPageable<T> {
    private Long currentPage;
    private Long numsPages;
    private List<T> data;
}
