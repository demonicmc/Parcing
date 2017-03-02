package ru.parser;

import ru.parser.model.HHStrategy;
import ru.parser.model.Model;
import ru.parser.model.Provider;
import ru.parser.view.HtmlView;

import java.io.IOException;

/**
 * Created by set on 28.02.17.
 */
public class Aggregator {
    public static void main(String[] args) throws IOException {
        Provider provider = new Provider(new HHStrategy());
        HtmlView view = new HtmlView();
        Model model = new Model(view,provider);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();

    }
}
