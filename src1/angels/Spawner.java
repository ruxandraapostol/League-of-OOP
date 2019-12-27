package angels;

import players.*;

public class Spawner extends Angels {
    public Spawner (final String type, final String predicate, final int round,
                    final int line, final int column) {
        super(type,predicate, round, line, column);
    }

    public class Constants {
        public static final int HPSPAWNERK = 200;
        public static final int HPSPAWNERP = 150;
        public static final int HPSPAWNERR = 180;
        public static final int HPSPAWNERW = 120;
    }

    public void angelPower(Heroes hero, int newHP ){
        if(hero.getHitPoints() <= 0) {
          hero.setHitPoints(newHP);  
        }
    }

    public final void angelPlay(Knight knight) {
        angelPower(knight, Constants.HPSPAWNERK);
    }

    public final void angelPlay(Pyromancer pyromancer) {
        angelPower(pyromancer, Constants.HPSPAWNERP);
    }

    public final void angelPlay(Wizard wizard) {
        angelPower(wizard, Constants.HPSPAWNERW);
    }

    public final void angelPlay(Rogue rogue) {
        angelPower(rogue, Constants.HPSPAWNERR);
    }
    
}
