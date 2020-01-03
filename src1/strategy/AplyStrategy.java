package strategy;

import players.Heroes;

public class AplyStrategy {
    public Strategy strategy;

    public AplyStrategy (Strategy strategy){
        this.strategy = strategy;
    }

    public final void executeStrategy (Heroes hero, float hp, float damage){
        strategy.operation(hero, hp, damage);
    }
}
