package players;

import angels.Angels;
import strategy.AplyStrategy;
import strategy.DeffenciveStrategy;
import strategy.OffenciveStrategy;

public class Rogue extends Heroes {
    private int nr = 2; //verifica daca e runda de criticalHit
    public Rogue(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter) {
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(final Heroes h, final String s) {
        h.fight(this, s);
    }

    public void acceptAngel (final Angels angels) {
        angels.angelPlay(this);
    }

    private static class Modifiers {
        public static final float DAMAGE1 = 200f;
        public static final float DAMAGE2 = 40f;
        public static final float DAMAGE1BONUS = 20f;
        public static final float DAMAGE2BONUS = 10f;
        public static final int DOT = 40;
        public static final int BONUS = 10;
        public static final int INDEX = 3;

        public static final float RVSR1 = 1.2f;
        public static final float RVSK1 = 0.9f;
        public static final float RVSP1 = 1.25f;
        public static final float RVSW1 = 1.25f;

        public static final float RVSR2 = 0.9f;
        public static final float RVSK2 = 0.8f;
        public static final float RVSP2 = 1.2f;
        public static final float RVSW2 = 1.25f;

        public static final float LAND = 1.15f;
        public static final float CRITICAL = 1.5f;

        public static final float GOODSTRATEGY = 0.5f;
        public static final float BADSTRATEGY = -0.2f;
        public static final float BADHP = 2;
        public static final int SEVEN = 7;
        public static final int FIVE = 5;

    }

    /**
     * Calculeaza cat damage da un Rogue doar prin paralysis
     * fara a lua in considerare modificatorii de rasa. Ia
     * in considerare modificatorii de teren.
     * @param s indica suprafata pe care va fi batalia
     * @return returneaza damage-ul
     */
    @Override
    public int totalDamage(final String s) {
        float mod = 1;
        if (s.equals("W")) {
            mod = Modifiers.LAND;
        }
        return Math.round((Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                * this.getLevel())  * mod);
    }

    /**
     * Calculez separat ce damage ar da un Rogue prin backstab
     * pentru a putea verifica daca sunt intr-o runda in care
     * trebuie dat criticalHit.
     * @param criticalHit daca nu sunt intr-o astfel de runda va
     *                    fi transmis ca 1 pentru a nu afecta
     * @return returneaza damage-ul
     */
    public final int totalBackstab(final float criticalHit) {
        float mod = 1;
        if (criticalHit != 1) {
            mod = Modifiers.LAND;
        }
        return (int) Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()) * mod * criticalHit);
    }
    /**
     * Calculez damage-ul dat si il scad din hp-ul victimei.
     * Verific daca trepuie sa modific Dot-ul victime, tinand cont de
     * modificatorii de rasa si de teren. De asemenea, vad daca trebuie
     * dat critical hit
     * @param h reprezinta victima
     * @param backstab modificatorul de rasa ce trebuie aplicat pentru
     *                 abilitatea backstab
     * @param paralysis modificatorul de rasa ce trebuie aplicat pentru
     *                  abilitatea paralysis
     * @param s suprafata de teren
     */
    public final void newScore(final Heroes h,
              final float backstab, final float paralysis, final String s) {
        if (h.getParalyzed() == 0) {
            AplyStrategy aplyStrategy;
            if (this.getHitPoints() > this.getMaxLevelHP()
                    / Modifiers.SEVEN && this.getHitPoints()
                    < this.getMaxLevelHP() / Modifiers.FIVE) {
                aplyStrategy = new AplyStrategy(new OffenciveStrategy());
                aplyStrategy.executeStrategy(this,
                        Modifiers.SEVEN, Modifiers.GOODSTRATEGY);

            } else if (this.getHitPoints() < this.getMaxLevelHP()
                    / Modifiers.SEVEN) {
                aplyStrategy = new AplyStrategy(new DeffenciveStrategy());
                aplyStrategy.executeStrategy(this,
                        Modifiers.BADHP, Modifiers.BADSTRATEGY);
            }
        }
        nr++;
        float mod = 1;
        float criticalHit = 1;

        // am grija sa aplic DoT in funnctie de modificatorul de teren si criticalHit
        // si setez incapacitatea adversarului
        if (s.equals("W")) {
            mod = Modifiers.LAND;
            h.setDot1(Math.round((Modifiers.DOT + Modifiers.BONUS * this.getLevel())
                    * mod * paralysis), 2 * Modifiers.INDEX);
            if (nr == Modifiers.INDEX) {
                criticalHit = Modifiers.CRITICAL;
                nr = 0;
            }
        } else {
            h.setDot1(Math.round((Modifiers.DOT + Modifiers.BONUS * this.getLevel())
                    * mod * paralysis), Modifiers.INDEX);
            if (nr == Modifiers.INDEX) {
                nr = 0;
            }
        }

        //calculez hp ul ce trebuie scazut victimei
        int result1 = Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()) * (backstab + this.getStrategy()
                + this.getAngelsModifyer()) * mod * criticalHit);
        int result2 = Math.round((Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                * this.getLevel()) * (paralysis + this.getStrategy()
                + this.getAngelsModifyer()) * mod);
        h.setHitPoints(h.getHitPoints() - result1 - result2);

    }

    //In functie de instanta obiectul cu care interactionaza
    //va fi aplicata metoda corespunzatoare, care va apela metoda de
    // calculare a damage-ului dupa formulele din enunt
    public final void fight(final Pyromancer hero, final String s) {
        newScore(hero, Modifiers.RVSP1, Modifiers.RVSP2, s);
    }

    public final void fight(final Wizard hero, final String s) {
        newScore(hero, Modifiers.RVSW1, Modifiers.RVSW2, s);
    }

    public final void fight(final Knight hero, final String s) {
        newScore(hero, Modifiers.RVSK1, Modifiers.RVSK2, s);
    }

    public final void fight(final Rogue hero, final String s) {
        newScore(hero, Modifiers.RVSR1, Modifiers.RVSR2, s);
    }

}
