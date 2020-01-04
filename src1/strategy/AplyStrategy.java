package strategy;

import players.Heroes;

public class AplyStrategy {
    private Strategy strategy;

    public AplyStrategy(final Strategy strategy) {
        this.strategy = strategy;
    }

    //in functie de context se alege strategia necesara
    public final void executeStrategy(final Heroes hero,
                     final float hp, final float damage) {
        strategy.operation(hero, hp, damage);
    }
}
