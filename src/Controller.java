import model.Provider;
import vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by set on 28.02.17.
 */
public class Controller {
    private Provider[] providers;

    public Controller(Provider...providers){
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
    }

    public void scan() {
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

    }
}
