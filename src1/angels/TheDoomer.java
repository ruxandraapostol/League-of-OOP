package angels;

import players.*;

public class TheDoomer extends Angels {

    public TheDoomer(final String type, final String predicate, final int round
            , final int line, final int column) {
        super(type, predicate, round, line, column);
    }

    public void angelPlay(Knight knight) {
        knight.setHitPoints(0);
    }

    public void angelPlay(Pyromancer pyromancer) {
        pyromancer.setHitPoints(0);
    }

    public void angelPlay(Wizard wizard) {
        wizard.setHitPoints(0);
    }

    public void angelPlay(Rogue rogue) {
        rogue.setHitPoints(0);
    }

}
