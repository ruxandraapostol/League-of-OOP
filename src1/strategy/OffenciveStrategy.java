package strategy;

import players.Heroes;

public class OffenciveStrategy implements Strategy {
    /**
     * Aceasta metoda se aplica in cazul in care un jucator
     * este nevoit sa plice strategia ofensiva.
     * @param hero - jucatorul in cauza
     * @param hp - partea pe care o va pierde din hp-ul lui initial
     * @param damage - modificatorul de demage cu care va creste
     *               modificatorul de rasa
     */
    @Override
    public void operation(final Heroes hero, final float hp, final float damage) {
        hero.setStrategy(hero.getStrategy() + damage);
        int newHp = (int) (hero.getHitPoints() - hero.getHitPoints() / hp);
        hero.setHitPoints(newHp);
    }
}
