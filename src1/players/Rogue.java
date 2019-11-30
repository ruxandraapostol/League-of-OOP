package players;

public class Rogue extends Heroes {
    private int nr = 2;
    private String advantage = null;
    public Rogue(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(Heroes h, String s) {
        h.fight(this, s);
    }

    private class Modifiers {
        public static final float DAMAGE1 = 200;
        public static final float DAMAGE2 = 40;
        public static final float DAMAGE1BONUS = 20;
        public static final float DAMAGE2BONUS = 10;
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
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    @Override
    public final int totalDamage(){
        return (int) Math.round(Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS * this.getLevel()) +
                (int) Math.round(Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS * this.getLevel());
    }

    public final void newScore(final Heroes h,
              final float constant1, final float constant, String s) {
        nr++;
        float mod = 1;
        float criticalHit = 1;
        if (s.equals("W")) {
            mod = Modifiers.LAND;
            h.setDot1(Math.round((Modifiers.DOT + Modifiers.BONUS * this.getLevel()) * mod * constant) , 2 * Modifiers.INDEX);
            if (nr == 3){
                criticalHit = 1.5f;
                nr = 0;
            }
        } else {
            h.setDot1(Modifiers.DOT + Modifiers.BONUS * this.getLevel(), Modifiers.INDEX);
            if(nr == 3) {
                nr = 0;
            }
        }
        int result = (int) Math.round(criticalHit* mod * constant1
                * (Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()));
        result += (int) Math.round(mod * constant * (Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                * this.getLevel())) ;
        h.setHitPoints(h.getHitPoints() - (int) result );

        if (h.getHitPoints() <= 0){
            h.setHitPoints(0);
            this.setExperiencePoints(h.getLevel());
        }
    }

    public final void fight(Pyromancer hero, String s){
        newScore(hero, Modifiers.RVSP1, Modifiers.RVSP2, s);
    }

    public final void fight (Wizard hero, String s){
        newScore(hero, Modifiers.RVSW1, Modifiers.RVSW2, s);
    }

    public final void fight (Knight hero, String s){
        newScore(hero, Modifiers.RVSK1, Modifiers.RVSK2, s);
    }

    public final void fight (Rogue hero, String s){
        newScore(hero, Modifiers.RVSR1, Modifiers.RVSR2, s);
    }

}
