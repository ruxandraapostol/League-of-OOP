package angels;

import players.*;

public class LevelUpAngel extends Angels {
    public LevelUpAngel(final String type, final String predicat, final int round, int line, int column) {
        super(type, predicat, round, line, column);
    }

    public static class Constants {
        public static final int TWOHUNDREDFIFTY = 250;
        public static final int FIFTY = 50;

        public static final float DAMAGELEVELUPK = 1.1f;
        public static final float DAMAGELEVELUPP = 1.2f;
        public static final float DAMAGELEVELUPR = 1.15f;
        public static final float DAMAGELEVELUPW = 1.25f;
    }

    public void angelPower(Heroes hero, float angelDamage ){
        if (hero.getHitPoints() <= 0){
            return;
        }
        hero.setAngelsModifyer(angelDamage);
        hero.setXPByAngel((hero.getLevel() + 1) * Constants.FIFTY + Constants.TWOHUNDREDFIFTY);
    }

    public final void angelPlay(Knight knight) {
        angelPower(knight, Constants.DAMAGELEVELUPK);
    }

    public final void angelPlay(Pyromancer pyromancer) {
        angelPower(pyromancer, Constants.DAMAGELEVELUPP);
    }

    public final void angelPlay(Wizard wizard) {
        angelPower(wizard, Constants.DAMAGELEVELUPW);
    }

    public final void angelPlay(Rogue rogue) {
        angelPower(rogue, Constants.DAMAGELEVELUPR);
    }
}
