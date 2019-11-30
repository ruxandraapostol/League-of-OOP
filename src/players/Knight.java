package players;

public class Knight extends Heroes {
    public Knight(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    private class Modifiers {
        public static final double DAMAGE1 = 200;
        public static final double DAMAGE2 = 100;
        public static final double DAMAGE1BONUS = 30;
        public static final double DAMAGE2BONUS = 40;

        public static final double KVSR1 = 1.15;
        public static final double KVSK1 = 1;
        public static final double KVSP1 = 1.1;
        public static final double KVSW1 = 0.8;

        public static final double KVSR2 = 0.8;
        public static final double KVSK2 = 1.2;
        public static final double KVSP2 = 0.9;
        public static final double KVSW2 = 1.05;
    }

    @Override
    public final int totalDamage(){
        return (int) Math.round(Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS * this.getLevel()) +
                (int) Math.round(Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS * this.getLevel());
    }

    public void newScore( Heroes h, double constant1, double constant2) {
        double conditon = (0.2 + 0.01 * this.getLevel()) * HeroesFactory.
                getInstance().getHeroesByLetter(h.getLetter()).getHitPoints();
        if ( h.getHitPoints() < conditon && this.getLevel() <= 20){
            h.setHitPoints(0);
            return;
        }

        int result = (int) Math.round(constant1 * (Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()) * h.getHitPoints()) ;
        result += (int) Math.round(constant2 * (Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                * this.getLevel()) * h.getHitPoints()) ;
        h.setHitPoints(h.getHitPoints() - result );
    }

    public void fight (Heroes hero) {
        if(hero instanceof Pyromancer) {
            newScore(hero, Modifiers.KVSP1, Modifiers.KVSP2);
        } else if(hero instanceof Wizard) {
            newScore(hero, Modifiers.KVSW1, Modifiers.KVSW2);
        } else if(hero instanceof Rogue) {
            newScore(hero, Modifiers.KVSR1, Modifiers.KVSR2);
        } else {
            newScore(hero, Modifiers.KVSK1, Modifiers.KVSK2);
        }
    }
}
