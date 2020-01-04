package strategy;

import players.Heroes;

public class DeffenciveStrategy implements Strategy {
    /**
     * Aceasta metoda se aplica in cazul in care un jucator
     * este nevoit sa plice strategia defensiva.
     * @param hero - jucatorul in cauza
     * @param hp - partea pe care o va castiga din hp-ul lui initial
     * @param damage - modificatorul de demage care va fi scazut din
     *               modificatorii de rasa
     */
    @Override
    public void operation(final Heroes hero, final float hp, final float damage) {
        hero.setStrategy(hero.getStrategy() + damage);
        int newHp = (int) (hero.getHitPoints() + hero.getHitPoints() / hp);
        hero.setHitPoints(newHp);
    }
}
