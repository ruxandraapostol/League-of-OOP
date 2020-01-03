package angels;

import players.Rogue;
import players.Knight;
import players.Wizard;
import players.Pyromancer;

public class TheDoomer extends Angels {

    public TheDoomer(final String type, final String predicate, final int round,
                     final int line, final int column) {
        super(type, predicate, round, line, column);
    }

    public final void angelPlay(final Knight knight) {
        knight.setHitPoints(0);
    }

    public final void angelPlay(final Pyromancer pyromancer) {
        pyromancer.setHitPoints(0);
    }

    public final void angelPlay(final Wizard wizard) {
        wizard.setHitPoints(0);
    }

    public final void angelPlay(final Rogue rogue) {
        rogue.setHitPoints(0);
    }

}
