package ru.parser.model;

import ru.parser.vo.Vacancy;

import java.util.List;

/**
 * Created by set on 28.02.17.
 */
public class Provider {
    Strategy strategy;

    public Provider() {
    }

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public List<Vacancy> getJavaVacancies(String searchString){
        return strategy.getVacancies(searchString);
    }
}
