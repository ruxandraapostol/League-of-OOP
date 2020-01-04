package angels;

import players.Heroes;
import players.Rogue;
import players.Knight;
import players.Wizard;
import players.Pyromancer;

public class Spawner extends Angels {
    public Spawner(final String type, final String predicate, final int round,
                    final int line, final int column) {
        super(type, predicate, round, line, column);
    }

    private class Constants {
        public static final int HPSPAWNERK = 200;
        public static final int HPSPAWNERP = 150;
        public static final int HPSPAWNERR = 180;
        public static final int HPSPAWNERW = 120;
    }

    //invie mortii
    public final void angelPower(final Heroes hero, final int newHP) {
        if (hero.getHitPoints() <= 0) {
            hero.setHitPoints(newHP);
        }
    }

    public final void angelPlay(final Knight knight) {
        angelPower(knight, Constants.HPSPAWNERK);
    }

    public final void angelPlay(final Pyromancer pyromancer) {
        angelPower(pyromancer, Constants.HPSPAWNERP);
    }

    public final void angelPlay(final Wizard wizard) {
        angelPower(wizard, Constants.HPSPAWNERW);
    }

    public final void angelPlay(final Rogue rogue) {
        angelPower(rogue, Constants.HPSPAWNERR);
    }
}
