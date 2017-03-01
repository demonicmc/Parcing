import model.HHStrategy;
import model.Provider;

/**
 * Created by set on 28.02.17.
 */
public class Aggregator {
    public static void main(String[] args) {
        Provider provider = new Provider(new HHStrategy());
        Controller controller = new Controller(provider);
        System.out.println(controller);
        controller.scan();

    }
}
