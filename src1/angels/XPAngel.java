package angels;

import players.*;

public class XPAngel extends Angels {
    public XPAngel(final String type, final String predicate, final int round
            , final int line, final int column) {
        super(type, predicate, round, line, column);
    }

    public class NewXP{
        public static final int XPK = 45;
        public static final int XPP = 50;
        public static final int XPR = 40;
        public static final int XPW = 60;
    }

    public void angelPower(Heroes hero, int newXP ){
        if (hero.getHitPoints() <= 0){
            return;
        }
        hero.setXPByAngel(hero.getExperiencePoints() + newXP);
    }

    public final void angelPlay(Knight knight) {
        angelPower(knight, NewXP.XPK);
    }

    public final void angelPlay(Pyromancer pyromancer) {
        angelPower(pyromancer, NewXP.XPP);
    }

    public final void angelPlay(Wizard wizard) {
        angelPower(wizard, NewXP.XPW);
    }

    public final void angelPlay(Rogue rogue) {
        angelPower(rogue, NewXP.XPR);
    }
}
