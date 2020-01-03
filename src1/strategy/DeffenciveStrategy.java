package strategy;

import players.Heroes;

public class DeffenciveStrategy implements Strategy {
    @Override
    public void operation(Heroes hero, float hp, float damage) {
        hero.setStrategy(hero.getStrategy() + damage);
        int newHp = (int) (hero.getHitPoints() + hero.getHitPoints() / hp);
        hero.setHitPoints(newHp);
    }
}
