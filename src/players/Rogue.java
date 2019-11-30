package players;

public class Rogue extends Heroes {
    private int nr = 0;
    private String advantage = null;
    public Rogue(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(Heroes h) {
        h.fight(this);
    }

    private class Modifiers {
        public static final double DAMAGE1 = 200;
        public static final double DAMAGE2 = 40;
        public static final double DAMAGE1BONUS = 20;
        public static final double DAMAGE2BONUS = 10;

        public static final double RVSR1 = 1.2;
        public static final double RVSK1 = 0.9;
        public static final double RVSP1 = 1.25;
        public static final double RVSW1 = 1.25;

        public static final double RVSR2 = 0.9;
        public static final double RVSK2 = 0.8;
        public static final double RVSP2 = 1.2;
        public static final double RVSW2 = 1.25;
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
              final double constant1, final double constant) {
        nr++;
        double result = 1;
        if(this.advantage == "W"){
            result = 1.5;
            if (nr == 2){
                result = 3;
            }
        } else if(nr == 2) {
            nr = 0;
        }
        
        result *= (int) Math.round(constant * (Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()));
        result += (int) Math.round(constant * (Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                * this.getLevel())) ;
        h.setHitPoints(h.getHitPoints() - (int) result );
    }

    public final void fight(Pyromancer hero){
        newScore(hero, Modifiers.RVSP1, Modifiers.RVSP2);
    }

    public final void fight (Wizard hero){
        newScore(hero, Modifiers.RVSW1, Modifiers.RVSW2);
    }

    public final void fight (Knight hero){
        newScore(hero, Modifiers.RVSK1, Modifiers.RVSK2);
    }

    public final void fight (Rogue hero){
        newScore(hero, Modifiers.RVSR1, Modifiers.RVSR2);
    }

}
