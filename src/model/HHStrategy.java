package model;

import vo.Vacancy;

import java.util.List;

/**
 * Created by set on 28.02.17.
 */
public class HHStrategy implements Strategy {

   private  final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";

   @Override
   public List<Vacancy> getVacancies(String searchString) {
      return null;
   }

//    public static void main(String[] args) {
//        System.out.println(String.format(URL_FORMAT, "Kiev", 3));
//    }

}
