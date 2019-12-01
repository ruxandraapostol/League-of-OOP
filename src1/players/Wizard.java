package players;

public class Wizard extends Heroes {
    private int nr = 2;
    public Wizard(final int hitPoints, final int bonusHitPoints,
                      final int experiencePoints, final int level, final String letter){
        super(hitPoints, bonusHitPoints, experiencePoints, level, letter);
    }

    public final void accept(Heroes h, String s) { h.fight(this, s); }

    private class Modifiers {
        public static final float DAMAGE1 = 0.2f;
        public static final float DAMAGE2 = 0.35f;
        public static final float DAMAGE1BONUS = 0.05f;
        public static final float DAMAGE2BONUS = 0.02f;

        public static final float WVSR1 = 0.8f;
        public static final float WVSK1 = 1.2f;
        public static final float WVSP1 = 0.9f;
        public static final float WVSW1 = 1.05f;

        public static final float WVSR2 = 1.2f;
        public static final float WVSK2 = 1.4f;
        public static final float WVSP2 = 1.3f;
        public static final float LAND = 1.1f;
        public static final float COND = 0.7f;
        public static final float PROC = 0.3f;
    }

    public void newScore(final Heroes h, final float drain, final float deflect, String s ) {
        float mod = 1;
        nr ++;
        if (s.equals("D")) {
            mod = Modifiers.LAND;
        }
        int result = (int) Math.round((Modifiers.DAMAGE1 + Modifiers.DAMAGE1BONUS
                * this.getLevel()) * (Math.min(h.getHitPoints(), Modifiers.PROC *
                (HeroesFactory.getInstance().getHeroesByLetter(h.getLetter()).getHitPoints() +
                        HeroesFactory.getInstance().getHeroesByLetter(h.getLetter()).
                        getBonusHitPoints() * h.getLevel() ))) * drain * mod ) ;

        float criticalHit = 1;
        float result2 = 0;
        if( h instanceof Pyromancer || h instanceof Knight || h instanceof Rogue){
            float procent = Modifiers.DAMAGE2 + Modifiers.DAMAGE2BONUS * this.getLevel();
            if ( procent > Modifiers.COND){
                procent = Modifiers.COND;
            }

            if(h instanceof Rogue) {
                if (nr == 3 && s.equals("W")) {
                        criticalHit = 1.5f;
                }
                result2 = ((Rogue) h).totalBackstab(criticalHit);
            }
            result2 = (int) Math.round(mod * deflect * procent * ( h.totalDamage(s) + result2) );
        }
        if(nr == 3){
            nr = 0;
        }
        h.setHitPoints(h.getHitPoints() - result - (int) result2);
    }

    public final void fight(Pyromancer hero, String s){
        newScore(hero, Modifiers.WVSP1, Modifiers.WVSP2, s);
    }

    public final void fight (Wizard hero, String s){
        newScore(hero, Modifiers.WVSW1, 0, s);
    }

    public final void fight (Knight hero, String s){
        newScore(hero, Modifiers.WVSK1, Modifiers.WVSK2, s);
    }

    public final void fight (Rogue hero, String s){
        newScore(hero, Modifiers.WVSR1, Modifiers.WVSR2, s);
    }
}
