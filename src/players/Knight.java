package players;

public class Knight extends Heroes {
    public Knight(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(Heroes h) {
        h.fight(this);
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

    public final void fight(Pyromancer hero){
        newScore(hero, Modifiers.KVSP1, Modifiers.KVSP2);
    }

    public final void fight (Wizard hero){
        newScore(hero, Modifiers.KVSW1, 0);
    }

    public final void fight (Knight hero){
        newScore(hero, Modifiers.KVSK1, Modifiers.KVSK2);
    }

    public final void fight (Rogue hero){
        newScore(hero, Modifiers.KVSR1, Modifiers.KVSR2);
    }
}
