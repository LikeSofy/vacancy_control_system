package by.klishevich.vacancy_control_system.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {
    private List<T> data;
    private int total;
}
