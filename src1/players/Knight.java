package players;

public class Knight extends Heroes {
    public Knight(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(Heroes h, String s) {
        h.fight(this, s);
    }

    private class Modifiers {
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
    }

    @Override
    public final int totalDamage( String s){
        float mod = 1;
        if (s.equals("L")) {
            mod = Modifiers.LAND;
        }
        return (int) Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS * this.getLevel()) * mod) +
                (int) Math.round((Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS * this.getLevel())*mod);
    }

    public void newScore( Heroes h, float execute, float slam, String s) {
        float conditon = (float) (0.2 + 0.01 * this.getLevel()) * HeroesFactory.
                getInstance().getHeroesByLetter(h.getLetter()).getHitPoints();
        if ( h.getHitPoints() < conditon && this.getLevel() <= 20) {
            h.setHitPoints(0);
        } else {
            float mod = 1;
            if (s.equals("L")) {
                mod = Modifiers.LAND;
            }
            int result = (int) Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                    * this.getLevel()) * execute * mod);
            result += (int) Math.round((Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                    * this.getLevel()) * slam * mod);
            h.setHitPoints(h.getHitPoints() - result);
        }
    }

    public final void fight(Pyromancer hero, String s){
        newScore(hero, Modifiers.KVSP1, Modifiers.KVSP2, s);
    }

    public final void fight (Wizard hero, String s){
        newScore(hero, Modifiers.KVSW1, Modifiers.KVSW2 , s);
    }

    public final void fight (Knight hero, String s){
        newScore(hero, Modifiers.KVSK1, Modifiers.KVSK2, s);
    }

    public final void fight (Rogue hero, String s){
        newScore(hero, Modifiers.KVSR1, Modifiers.KVSR2, s);
    }
}
