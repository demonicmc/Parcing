package model;

/**
 * Created by set on 28.02.17.
 */
public class Provider {
    Strategy strategy;

    public Provider() {
    }

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
