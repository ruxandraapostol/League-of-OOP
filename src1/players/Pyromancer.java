package players;

public class Pyromancer extends Heroes{
    public Pyromancer(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(Heroes h, String s) {
        h.fight(this, s);
    }

    private class Modifiers {
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

    @Override
    public final int totalDamage(String s){
        float mod = 1;
        if (s.equals("V")) {
            mod = Modifiers.LAND;
        }
        return (int) Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS * this.getLevel()) * mod) +
                (int) Math.round((Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS * this.getLevel()) * mod);
    }

    public void newScore( Heroes h, float ability, String s) {
        float mod = 1;
        if (s.equals("V")) {
            mod = Modifiers.LAND;
        }
        h.setDot1(Math.round((Modifiers.DOT + Modifiers.BONUS * this.getLevel()) * mod * ability), Modifiers.INDEX);
        int result = (int) Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel())*ability*mod) ;
        result += (int) Math.round((Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS
                * this.getLevel()) * ability * mod) ;
        h.setHitPoints(h.getHitPoints() - result);
        if (h.getHitPoints() <= 0){
            h.setHitPoints(0);
            this.setExperiencePoints(h.getLevel());
        }
    }

    public final void fight(Pyromancer hero, String s){
        newScore(hero, Modifiers.PVSP, s);
    }

    public final void fight (Wizard hero, String s){
        newScore(hero, Modifiers.PVSW, s);
    }

    public final void fight (Knight hero, String s){
        newScore(hero, Modifiers.PVSK, s);
    }

    public final void fight (Rogue hero, String s){
        newScore(hero, Modifiers.PVSR, s);
    }
}
