package ru.parser.model;

import ru.parser.export.Export;
import ru.parser.view.View;
import ru.parser.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by set on 01.03.17.
 */
public class Model {
    private View view;
    private Provider[] providers;
    private Export export;

    public void setExport(Export export) {
        this.export = export;
    }

    public Model(View view, Provider... providers){
        if (view==null || providers==null || providers.length==0)
            throw new IllegalArgumentException();
        else {
            this.view = view;
            this.providers = providers;}
    }

    public void selectCity(String city) throws IOException {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider p:providers) {
                vacancies.addAll(p.getJavaVacancies(city));
        }
        export.export(vacancies);
        view.update(vacancies);
    }
}
