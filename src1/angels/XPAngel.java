package angels;

import players.Heroes;
import players.Rogue;
import players.Knight;
import players.Wizard;
import players.Pyromancer;

public class XPAngel extends Angels {
    public XPAngel(final String type, final String predicate, final int round,
                   final int line, final int column) {
        super(type, predicate, round, line, column);
    }

    public class NewXP {
        public static final int XPK = 45;
        public static final int XPP = 50;
        public static final int XPR = 40;
        public static final int XPW = 60;
    }

    public final void angelPower(final Heroes hero, final int newXP) {
        if (hero.getHitPoints() <= 0) {
            return;
        }
        hero.setXPByAngel(hero.getExperiencePoints() + newXP);
    }

    public final void angelPlay(final Knight knight) {
        angelPower(knight, NewXP.XPK);
    }

    public final void angelPlay(final Pyromancer pyromancer) {
        angelPower(pyromancer, NewXP.XPP);
    }

    public final void angelPlay(final Wizard wizard) {
        angelPower(wizard, NewXP.XPW);
    }

    public final void angelPlay(final Rogue rogue) {
        angelPower(rogue, NewXP.XPR);
    }
}
