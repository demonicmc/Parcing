package ru.parser.view;

import ru.parser.Controller;
import ru.parser.vo.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Created by set on 01.03.17.
 */
public interface View {
    void update(List<Vacancy> vacancies) throws IOException;
    void setController(Controller controller);
}
