package players;

import angels.Angels;
import strategy.AplyStrategy;
import strategy.DeffenciveStrategy;
import strategy.OffenciveStrategy;

public class Knight extends Heroes {
    public Knight(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter) {
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(final Heroes h, final String s) {
        h.fight(this, s);
    }

    public final void acceptAngel(final Angels angels)  {
        angels.angelPlay(this);
    }

    private static class Modifiers {
        public static final float DAMAGE1 = 200;
        public static final float DAMAGE2 = 100;
        public static final float DAMAGE1BONUS = 30;
        public static final float DAMAGE2BONUS = 40;

        public static final float KVSR1 = 1.15f;
        public static final float KVSK1 = 1;
        public static final float KVSP1 = 1.1f;
        public static final float KVSW1 = 0.8f;

        public static final float KVSR2 = 0.8f;
        public static final float KVSK2 = 1.2f;
        public static final float KVSP2 = 0.9f;
        public static final float KVSW2 = 1.05f;

        public static final float LAND = 1.15f;
        public static final float HPPROCENT = 0.2f;
        public static final float BONUSPROCENT = 0.01f;
        public static final float COND = 20;

        public static final float GOODSTRATEGY = 0.5f;
        public static final float GOODHP = 5;
        public static final float BADSTRATEGY = -0.2f;
        public static final float BADSHP = 4;
        public static final int TWO = 2;
        public static final int  THREE = 3;
    }

    /**
     * Aceasta metoda va alege in functie de nivelul de hp al
     * jucatorului in cauza daca este cazul sa aplice sau nu
     * o strategie ofensiva/defensiva, tinand cont de enuntul
     * problemei.
     */
    @Override
    public void chooseStrategy() {
        AplyStrategy aplyStrategy;
        if (this.getParalyzed() == 0) {
            if (this.getHitPoints() > this.getMaxLevelHP()
                    / Modifiers.THREE && this.getHitPoints()
                    < this.getMaxLevelHP() / Modifiers.TWO) {
                aplyStrategy = new AplyStrategy(new OffenciveStrategy());
                aplyStrategy.executeStrategy(this,
                        Modifiers.GOODHP, Modifiers.GOODSTRATEGY);

            } else if (this.getHitPoints() < this.getMaxLevelHP()
                    / Modifiers.THREE) {
                aplyStrategy = new AplyStrategy(new DeffenciveStrategy());
                aplyStrategy.executeStrategy(this,
                        Modifiers.BADSHP, Modifiers.BADSTRATEGY);
            }
        }
    }

    /**
     * Calculeaza cat damage da un Knight fara a lua
     * in considerare modificatorii de rasa. Ia in considerare
     * modificatorii de teren.
     * @param s indica suprafata pe care va fi batalia
     * @return returneaza damage-ul
     */
    @Override
    public int totalDamage(final String s) {
        float mod = 1;
        if (s.equals("L")) {
            mod = Modifiers.LAND;
        }
        return Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS * this.getLevel())
                * mod) + Math.round((Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                * this.getLevel())  * mod);
    }

    /**
     * Calculez damage-ul pe care dat si il scad din hp-ul victimei.
     * Am grija ca daca hp-ul victimei este mai mic decat limita din
     * enunt sa il omor instant. Aplic incapacitatea.
     * @param h victima
     * @param execute modificatorul de rasa ce trebuie aplicat pentru
     *              abilitatea execute
     * @param slam modificatorul de rasa ce trebuie aplicat pentru
     *              abilitatea slam
     * @param s suprafata de teren
     */
    public final void newScore(final Heroes h, final float execute,
                                final float slam, final String s) {
        //calculez limita minima de hp
        float conditon = (Modifiers.HPPROCENT + Modifiers.BONUSPROCENT
                * this.getLevel()) * (HeroesFactory.getInstance().getHeroesByLetter(
                h.getLetter()).getHitPoints() + h.getLevel() * h.getBonusHitPoints());

        //daca e incalcata il omor instant
        if (h.getHitPoints() < conditon && this.getLevel() <= Modifiers.COND) {
            h.setHitPoints(0);
        } else {
            //modificator de teren
            float mod = 1;
            if (s.equals("L")) {
                mod = Modifiers.LAND;
            }
            //calculez hp ul ce trebuie scazut victimei
            int result;
            if (execute != 1) {
                result = Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                        * this.getLevel()) * (execute + this.getStrategy()
                        + Constants.APROX + this.getAngelsModifyer()) * mod);
            } else {
                result = Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                        * this.getLevel()) * (execute + this.getStrategy()) * mod);

            }
            int a = result;
            result += Math.round((Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                    * this.getLevel()) * (slam + this.getAngelsModifyer()
                    + Constants.APROX + this.getStrategy()) * mod);
            h.setHitPoints(h.getHitPoints() - result);
            h.setParalyzed(Modifiers.TWO);
            //System.out.println("Knight " + this.getId() + " execute = " + a +
            //        "; slam = " + (result-a) + " (angel : " + this.getAngelsModifyer()
            //        +" strategy : " + this.getStrategy() + ")");
        }
    }

    //In functie de instanta obiectul cu care interactionaza
    //va fi aplicata metoda corespunzatoare, care va apela metoda de
    // calculare a damage-ului dupa formulele din enunt
    public final void fight(final Pyromancer hero, final String s) {
        newScore(hero, Modifiers.KVSP1, Modifiers.KVSP2, s);
    }

    public final void fight(final Wizard hero, final String s) {
        newScore(hero, Modifiers.KVSW1, Modifiers.KVSW2, s);
    }

    public final void fight(final Knight hero, final String s) {
        newScore(hero, Modifiers.KVSK1, Modifiers.KVSK2, s);
    }

    public final void fight(final Rogue hero, final String s) {
        newScore(hero, Modifiers.KVSR1, Modifiers.KVSR2, s);
    }
}
