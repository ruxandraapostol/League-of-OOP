package players;

public class Rogue extends Heroes {
    private int nr = 0;
    public Rogue(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
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

    @Override
    public final int totalDamage(){
        return (int) Math.round(Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS * this.getLevel()) +
                (int) Math.round(Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS * this.getLevel());
    }

    public final void newScore(final Heroes h, final double
            constant1, final double constant, final String letter) {
        nr++;
        double result = 1;
        if(letter == "W"){
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

    public void fight (Heroes hero, String letter) {
        if(hero instanceof Pyromancer) {
            newScore(hero, Modifiers.RVSP1, Modifiers.RVSP2, letter);
        } else if(hero instanceof Wizard) {
            newScore(hero, Modifiers.RVSW1, Modifiers.RVSW2, letter);
        } else if(hero instanceof Rogue) {
            newScore(hero, Modifiers.RVSR1, Modifiers.RVSR2, letter);
        } else {
            newScore(hero, Modifiers.RVSK1, Modifiers.RVSK2, letter);
        }
    }
}
