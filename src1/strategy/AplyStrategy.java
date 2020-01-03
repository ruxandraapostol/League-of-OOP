package strategy;

import players.Heroes;

public class AplyStrategy {
    private Strategy strategy;

    public AplyStrategy (Strategy strategy){
        this.strategy = strategy;
    }

    public void executeStrategy (Heroes hero, float hp, float damage){
        strategy.operation(hero, hp, damage);
    }
}
