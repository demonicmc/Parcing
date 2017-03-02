package ru.parser.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.parser.Controller;
import ru.parser.vo.Vacancy;

import java.io.*;
import java.util.List;

/**
 * Created by set on 01.03.17.
 */
public class HtmlView implements View {

    private Controller controller;

    private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    public HtmlView() {
    }

    public HtmlView(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() throws IOException{
        controller.onCitySelect("спб");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException {
        Document document = getDocument();
        System.out.println(document.body());
        Element element = document.getElementsByClass("template").first();
        if(element == null){
            System.out.println("Element = NULL!!!!!!!\n\rNULLL!!!!!!!");
        }
        Element cloneElement = element.clone();
        cloneElement.removeClass("template").removeAttr("style");
        document.getElementsByAttributeValue("class", "vacancy").remove();

        for (Vacancy vacancy : vacancies)
        {
            Element vac = cloneElement.clone();
            vac.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
            vac.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
            vac.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
            Element link = vac.getElementsByTag("a").get(0);
            link.text(vacancy.getTitle());
            link.attr("href", vacancy.getUrl());
            element.before(vac.outerHtml());
        }
        return document.html();
    }

    private void updateFile(String str) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
        writer.println(str);
        writer.close();
    }

    protected Document getDocument() throws IOException{
        File file = new File(filePath);
        return Jsoup.parse(file, "UTF-8");
    }

    @Override
    public void update(List<Vacancy> vacancies) throws IOException {
       try {
           updateFile(getUpdatedFileContent(vacancies));
       } catch (IOException e){
           e.printStackTrace();
           System.out.println("Some exception occurred");
       }

    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

}
