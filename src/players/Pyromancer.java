package players;

public class Pyromancer extends Heroes{
    public Pyromancer(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(Heroes h) {
        h.fight(this);
    }

    private class Modifiers {
        public static final double DAMAGE1 = 350;
        public static final double DAMAGE2 = 150;
        public static final double DAMAGE1BONUS = 0;
        public static final double DAMAGE2BONUS = 20;

        public static final double PVSR = 0.8;
        public static final double PVSK = 1.2;
        public static final double PVSP = 0.9;
        public static final double PVSW = 1.05;
    }

    @Override
    public final int totalDamage(){
        return (int) Math.round(Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS * this.getLevel()) +
                (int) Math.round(Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS * this.getLevel());
    }

    public void newScore( Heroes h, double constant) {
        int result = (int) Math.round(constant * (Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()) * h.getHitPoints()) ;
        result += (int) Math.round(constant * (Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                * this.getLevel()) * h.getHitPoints()) ;
        h.setHitPoints(h.getHitPoints() - result );
    }

    public final void fight(Pyromancer hero){
        newScore(hero, Modifiers.PVSP);
    }

    public final void fight (Wizard hero){
        newScore(hero, Modifiers.PVSW);
    }

    public final void fight (Knight hero){
        newScore(hero, Modifiers.PVSK);
    }

    public final void fight (Rogue hero){
        newScore(hero, Modifiers.PVSR);
    }
}
