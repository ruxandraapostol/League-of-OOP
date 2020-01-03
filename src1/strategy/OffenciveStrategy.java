package strategy;

import players.Heroes;

public class OffenciveStrategy implements Strategy {
    @Override
    public void operation(Heroes hero, float hp, float damage) {
        hero.setStrategy(damage);
        int newHp = (int) (hero.getHitPoints() + hero.getHitPoints() / hp);
        hero.setHitPoints(newHp);
    }
}
