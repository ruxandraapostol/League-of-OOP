package players;

public class Pyromancer extends Heroes {
    public Pyromancer(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter) {
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(final Heroes h, final String s) {
        h.fight(this, s);
    }

    private static class Modifiers {
        public static final float DAMAGE1 = 350;
        public static final float DAMAGE2 = 150;
        public static final float DAMAGE1BONUS = 0;
        public static final float DAMAGE2BONUS = 20;
        public static final int DOT = 50;
        public static final int BONUS = 30;
        public static final int INDEX = 2;

        public static final float PVSR = 0.8f;
        public static final float PVSK = 1.2f;
        public static final float PVSP = 0.9f;
        public static final float PVSW = 1.05f;
        public static final float LAND = 1.25f;
    }

    /**
     * Calculeaza cat damage da un Pyromancer fara a lua
     * in considerare modificatorii de rasa. Ia in considerare
     * modificatorii de teren.
     * @param s indica suprafata pe care va fi batalia
     * @return returneaza damage-ul
     */
    @Override
    public int totalDamage(final String s) {
        float mod = 1;
        if (s.equals("V")) {
            mod = Modifiers.LAND;
        }
        return Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()) * mod) + Math.round((Modifiers.DAMAGE2
                + Modifiers.DAMAGE2BONUS * this.getLevel()) * mod);
    }
    /**
     * Calculez damage-ul dat si il scad din hp-ul victimei.
     * Verific daca trepuie sa modific Dot-ul victime.
     * @param h reprezinta victima
     * @param ability modificatorul de rasa ce trebuie aplicat atat pentru
     *                 fireblast, cat si pentru ignite
     * @param s suprafata de teren
     */
    public final void newScore(final Heroes h, final float ability, final String s) {
        //verific daca am modificator de teren
        float mod = 1;
        if (s.equals("V")) {
            mod = Modifiers.LAND;
        }

        //dau Dot
        h.setDot1(Math.round((Modifiers.DOT + Modifiers.BONUS * this.getLevel())
                * mod * ability), Modifiers.INDEX);

        //calculez hp ul ce trebuie scazut victimei
        int result = Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()) * ability * mod);
        result += Math.round((Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                * this.getLevel()) * ability * mod);
        h.setHitPoints(h.getHitPoints() - result);
    }

    //In functie de instanta obiectul cu care interactionaza
    //va fi aplicata metoda corespunzatoare, care va apela metoda de
    // calculare a damage-ului dupa formulele din enunt
    public final void fight(final Pyromancer hero, final String s) {
        newScore(hero, Modifiers.PVSP, s);
    }

    public final void fight(final Wizard hero, final String s) {
        newScore(hero, Modifiers.PVSW, s);
    }

    public final void fight(final Knight hero, final String s) {
        newScore(hero, Modifiers.PVSK, s);
    }

    public final void fight(final Rogue hero, final String s) {
        newScore(hero, Modifiers.PVSR, s);
    }
}
