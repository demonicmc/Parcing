import model.Provider;

import java.util.Arrays;

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
}
