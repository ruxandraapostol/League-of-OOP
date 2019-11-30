package players;

public class Wizard extends Heroes {
    public Wizard(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    private class Modifiers {
        public static final double DAMAGE1 = 0.2;
        public static final double DAMAGE2 = 0.35;
        public static final double DAMAGE1BONUS = 0.05;
        public static final double DAMAGE2BONUS = 0.02;

        public static final double WVSR1 = 0.8;
        public static final double WVSK1 = 1.2;
        public static final double WVSP1 = 0.9;
        public static final double WVSW1 = 1.05;

        public static final double WVSR2 = 1.2;
        public static final double WVSK2 = 1.4;
        public static final double WVSP2 = 1.3;

    }

    public void newScore1(final Heroes h, final double constant1, final double constant2 ) {
        int result = (int) Math.round(constant1 * (Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()) * (Math.min(h.getHitPoints(), 0.3 *
                HeroesFactory.getInstance().getHeroesByLetter(h.getLetter()).getHitPoints()))) ;
        if( h instanceof Pyromancer || h instanceof Knight || h instanceof Rogue){
            double procent = Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS * this.getLevel();
            if ( procent > 0.7){
                procent = 0.7;
            }
            result += (int) Math.round(constant2 * procent * h.totalDamage());
        }

        h.setHitPoints(h.getHitPoints() - result);
    }
    
    public void fight (Heroes hero) {
        if(hero instanceof Pyromancer) {
            newScore1(hero, Modifiers.WVSP1, Modifiers.WVSP2);
        } else if(hero instanceof Wizard) {
            newScore1(hero, Modifiers.WVSW1, 0);
        } else if(hero instanceof Rogue) {
            newScore1(hero, Modifiers.WVSR1, Modifiers.WVSR2);
        } else {
            newScore1(hero, Modifiers.WVSK1, Modifiers.WVSK2);
        }
    }
}
