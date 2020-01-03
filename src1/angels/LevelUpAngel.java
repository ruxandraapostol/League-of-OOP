package angels;

import players.Heroes;
import players.Rogue;
import players.Knight;
import players.Wizard;
import players.Pyromancer;

public class LevelUpAngel extends Angels {
    public LevelUpAngel(final String type, final String predicat,
                        final int round, final int line, final int column) {
        super(type, predicat, round, line, column);
    }

    public static class Constants {
        public static final int TWOHUNDREDFIFTY = 250;
        public static final int FIFTY = 50;

        public static final float DAMAGELEVELUPK = 0.1f;
        public static final float DAMAGELEVELUPP = 0.2f;
        public static final float DAMAGELEVELUPR = 0.15f;
        public static final float DAMAGELEVELUPW = 0.25f;
    }

    public final void angelPower(final Heroes hero, final float angelDamage) {
        if (hero.getHitPoints() <= 0) {
            return;
        }
        hero.setAngelsModifyer(hero.getAngelsModifyer() + angelDamage);
        hero.setXPByAngel(hero.getLevel() * Constants.FIFTY + Constants.TWOHUNDREDFIFTY);
    }

    public final void angelPlay(final Knight knight) {
        angelPower(knight, Constants.DAMAGELEVELUPK);
    }

    public final void angelPlay(final Pyromancer pyromancer) {
        angelPower(pyromancer, Constants.DAMAGELEVELUPP);
    }

    public final void angelPlay(final Wizard wizard) {
        angelPower(wizard, Constants.DAMAGELEVELUPW);
    }

    public final void angelPlay(final Rogue rogue) {
        angelPower(rogue, Constants.DAMAGELEVELUPR);
    }
}
