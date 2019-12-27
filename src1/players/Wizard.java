package players;

import angels.Angels;

public class Wizard extends Heroes {
    private int nr = 2; // verific daca e runda de criticalHit
    public Wizard(final int hitPoints, final int bonusHitPoints,
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
        public static final float DAMAGE1 = 0.2f;
        public static final float DAMAGE2 = 0.35f;
        public static final float DAMAGE1BONUS = 0.05f;
        public static final float DAMAGE2BONUS = 0.02f;

        public static final float WVSR1 = 0.8f;
        public static final float WVSK1 = 1.2f;
        public static final float WVSP1 = 0.9f;
        public static final float WVSW1 = 1.05f;

        public static final float WVSR2 = 1.2f;
        public static final float WVSK2 = 1.4f;
        public static final float WVSP2 = 1.3f;

        public static final float LAND = 1.1f;
        public static final float COND = 0.7f;
        public static final float PROC = 0.3f;
        public static final int ROUNDS = 3;
        public static final float CRITICAL = 1.5f;

        public static final float GOODSTRATEGY = 1.5f;
        public static final float GOODHP = 0.9f;
        public static final float BADSTRATEGY = 0.8f;
        public static final float BADHP = 1.2f;
        public static final int TWO = 2;
        public static final int FOUR = 4;
    }

    /**
     * Calculez damage-ul dat si il scad din hp-ul victimei.
     * Verific daca trepuie sa modific Dot-ul victime, tinand cont de
     * modificatorii de rasa si de teren. De asemenea, vad daca trebuie
     * dat critical hit
     * @param h victima
     * @param drain modificatorul de rasa ce trebuie aplicat pentru
     *              abilitatea drain
     * @param deflect modificatorul de rasa ce trebuie aplicat pentru
     *              abilitatea deflect
     * @param s suprafata de teren
     */
    public final void newScore(final Heroes h, final float drain,
                               final float deflect, final String s) {

        int maxLevelHp = this.getLevel() * HeroesFactory.getInstance().
                getHeroesByLetter(this.getLetter()).getBonusHitPoints()
                + HeroesFactory.getInstance().getHeroesByLetter(this.getLetter()).getHitPoints();

        if (this.getHitPoints() >  maxLevelHp / Modifiers.FOUR && this.getHitPoints() < maxLevelHp / Modifiers.TWO) {
            this.setHitPoints((int) (this.getHitPoints() * Modifiers.GOODHP));
            this.setStrategy(Modifiers.GOODSTRATEGY);

        } else if (this.getHitPoints() < maxLevelHp / Modifiers.FOUR) {
            this.setHitPoints((int) (this.getHitPoints() * Modifiers.BADHP));
            this.setStrategy(Modifiers.BADSTRATEGY);
        }

        //modificator de teren
        float mod = 1;
        nr++;
        if (s.equals("D")) {
            mod = Modifiers.LAND;
        }
        //damage dat de drain cu formula din enunt
        int result = Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()) * (Math.min(h.getHitPoints(), Modifiers.PROC
                * (HeroesFactory.getInstance().getHeroesByLetter(h.getLetter()).
                getHitPoints() + HeroesFactory.getInstance().getHeroesByLetter(
                h.getLetter()).getBonusHitPoints() * h.getLevel())))
                * this.getAngelsModifyer() * this.getStrategy() * drain * mod);

        float criticalHit = 1;
        float result2 = 0;
        //calculez damage doar pentru victime ce nu sunt Wizard
        if (h.getLetter() != "W") {
            //stabilesc procentul acestei abilitati dupa formula
            float procent = Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS * this.getLevel();
            if (procent > Modifiers.COND) {
                procent = Modifiers.COND;
            }
            //am grija sa iau in calcul si criticalHit pentru Rogue
            if (h.getLetter().equals("R")) {
                if (nr == Modifiers.ROUNDS && s.equals("W")) {
                        criticalHit = Modifiers.CRITICAL;
                }
                result2 = ((Rogue) h).totalBackstab(criticalHit);
            }
            result2 = Math.round(mod * this.getAngelsModifyer() * this.getStrategy() * deflect * procent * (h.totalDamage(s) + result2));
        }
        if (nr == Modifiers.ROUNDS) {
            nr = 0;
        }
        //scad damage din viata victimei
        h.setHitPoints(h.getHitPoints() - result - (int) result2);
    }

    //In functie de instanta obiectul cu care interactionaza
    //va fi aplicata metoda corespunzatoare, care va apela metoda de
    // calculare a damage-ului dupa formulele din enunt
    public final void fight(final Pyromancer hero, final String s) {
        newScore(hero, Modifiers.WVSP1, Modifiers.WVSP2, s);
    }

    public final void fight(final Wizard hero, final String s) {
        newScore(hero, Modifiers.WVSW1, 0, s);
    }

    public final void fight(final Knight hero, final String s) {
        newScore(hero, Modifiers.WVSK1, Modifiers.WVSK2, s);
    }

    public final void fight(final Rogue hero, final String s) {
        newScore(hero, Modifiers.WVSR1, Modifiers.WVSR2, s);
    }
}
