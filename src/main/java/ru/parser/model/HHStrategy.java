package ru.parser.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parser.vo.Vacancy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by set on 28.02.17.
 */
public class HHStrategy implements Strategy {

   private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&page=%d";
//   private static final String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
//private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
//   String url = String.format(URL_FORMAT,"СПб",0);
   private static final String userAgent = "Chrome/55.0.2883.87";
   private static final String userAgent1 = "Mozilla/5.0 (jsoup)";
   private static final int timeout = 5 * 1000;
   private static final String urlTest = "http://javarush.ru/testdata/big28data.html";
//   private static final String referrer  = "http://google.com";
    private static final String referrer = "https://hh.ru/";



   @Override
   public List<Vacancy> getVacancies(String searchString) {
       Document doc = null;
       List<Vacancy> vacancies = new ArrayList<>();
       try{
           for(int i = 0; ;i++) {
               doc = getDocument(searchString, i);
               Elements all = doc.select("div.search-result-item");
               if (all.size() > 0) {
                   for (Element e : all) {
                       Vacancy vac = new Vacancy();
                       vac.setUrl(e.select("a.search-result-item__name").attr("href"));
                       vac.setTitle(e.select("a.search-result-item__name").text());
                       vac.setSalary(e.select("div.b-vacancy-list-salary").text());
                       vac.setCity(e.select("span.searchresult__address").text());
                       vac.setCompanyName(e.select("a.bloko-link_secondary").text());
                       vac.setSiteName("hh");

                       vacancies.add(vac);
                   }
               } else{

                   break;
               }
           }
       } catch(Exception e){

       }
       return vacancies;



   }

   protected Document getDocument(String searchString, int page) throws IOException{
       String url = String.format(URL_FORMAT, encode(searchString,"UTF-8"), page);
//       String url = String.format(URL_FORMAT, searchString, page);
//       String url = String.format(URL_FORMAT, searchString, page);
       Document doc = null;
       try {
            doc = Jsoup.connect(url).userAgent(userAgent).timeout(timeout).get();
       }catch (Exception e){
           e.getStackTrace();
       }
       return doc;
   }

    public static String encode(String var0, String var1) throws UnsupportedEncodingException {
        BitSet dontNeedEncoding = new BitSet(256);
        boolean var2 = false;
        boolean var3 = false;
        byte var4 = 10;
        StringBuffer var5 = new StringBuffer(var0.length());
        ByteArrayOutputStream var6 = new ByteArrayOutputStream(var4);
        OutputStreamWriter var7 = new OutputStreamWriter(var6, var1);

        for (int var8 = 0; var8 < var0.length(); ++var8) {
            char var9 = var0.charAt(var8);
            if (dontNeedEncoding.get(var9)) {
                if (var9 == 32) {
                    var9 = 43;
                    var2 = true;
                }

                var5.append((char) var9);
                var3 = true;
            } else {
                try {
                    if (var3) {
                        var7 = new OutputStreamWriter(var6, var1);
                        var3 = false;
                    }

                    var7.write(var9);
                    if (var9 >= '\ud800' && var9 <= '\udbff' && var8 + 1 < var0.length()) {
                        char var10 = var0.charAt(var8 + 1);
                        if (var10 >= '\udc00' && var10 <= '\udfff') {
                            var7.write(var10);
                            ++var8;
                        }
                    }

                    var7.flush();
                }
                catch (IOException var13) {
                    var6.reset();
                    continue;
                }

                byte[] var14 = var6.toByteArray();

                for (int var11 = 0; var11 < var14.length; ++var11) {
                    var5.append('%');
                    char var12 = Character.forDigit(var14[var11] >> 4 & 15, 16);
                    if (Character.isLetter(var12)) {
                        var12 = (char) (var12 - 32);
                    }

                    var5.append(var12);
                    var12 = Character.forDigit(var14[var11] & 15, 16);
                    if (Character.isLetter(var12)) {
                        var12 = (char) (var12 - 32);
                    }

                    var5.append(var12);
                }

                var6.reset();
                var2 = true;
            }
        }

        return var2 ? var5.toString() : var0;
    }
//    public static void main(String[] args) {
//        System.out.println(String.format(URL_FORMAT, "Kiev", 3));
//    }

}
