package ru.parser;

import ru.parser.model.Model;

import java.io.IOException;

/**
 * Created by set on 28.02.17.
 */
public class Controller {
//    private Provider[] providers;
    Model model;

    public Controller(Model model) {
        if (model == null)
            throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName) throws IOException {
        model.selectCity(cityName);
    }
/*  public Controller(Provider...providers){
        if(providers == null){
            throw new IllegalArgumentException();
        }
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }*/

    /*public void scan() {
        List<Vacancy> list =new ArrayList<>();
        String search = "";
        try
        {
           for(Provider p : providers){
             list.addAll(p.getJavaVacancies(search));
          }
       } catch (NullPointerException  e){

       } finally {System.out.println("Size of vacancies: " + list.size());
       }

    }*/
}
