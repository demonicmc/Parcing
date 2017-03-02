package ru.parser.export;

import ru.parser.vo.Vacancy;

import java.util.List;

/**
 * Created by set on 02.03.17.
 */
public interface Export {
    void export (List<Vacancy> vacancies);
}
