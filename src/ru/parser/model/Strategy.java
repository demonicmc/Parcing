package ru.parser.model;

import ru.parser.vo.Vacancy;

import java.util.List;

/**
 * Created by set on 28.02.17.
 */
public interface Strategy {
   List<Vacancy> getVacancies(String searchString);
}
