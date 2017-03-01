package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import vo.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Created by set on 28.02.17.
 */
public class HHStrategy implements Strategy {

   private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
//   private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
   String url = String.format(URL_FORMAT,"СПб",0);
   private static final String userAgent = "Mozilla/5.0 (jsoup)";
   private static final int timeout = 5 * 1000;
   private static final String urlTest = "http://javarush.ru/testdata/big28data.html";




   @Override
   public List<Vacancy> getVacancies(String searchString) {

      try {
         Document doc = Jsoup.connect(url).userAgent(userAgent).timeout(timeout).get();
         System.out.println(doc.html());
      } catch (IOException e) {
         e.printStackTrace();
      }

      return null;
   }

//    public static void main(String[] args) {
//        System.out.println(String.format(URL_FORMAT, "Kiev", 3));
//    }

}
