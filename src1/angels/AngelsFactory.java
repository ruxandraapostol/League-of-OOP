package angels;

public class AngelsFactory {
    private static AngelsFactory instance = null;

    public static class AssetHpAngels {
        public static final int HPDARKANGELK = -40;
        public static final int HPDARKANGELP = -30;
        public static final int HPDARKANGELR = -10;
        public static final int HPDARKANGELW = -20;

        public static final int HPDRACULAK = -60;
        public static final int HPDRACULAP = -40;
        public static final int HPDRACULAR = -35;
        public static final int HPDRACULAW = -20;

        public static final int HPGOODBOYK = 20;
        public static final int HPGOODBOYP = 30;
        public static final int HPGOODBOYR = 40;
        public static final int HPGOODBOYW = 50;

        public static final int HPLIFEGIVERK = 100;
        public static final int HPLIFEGIVERP = 80;
        public static final int HPLIFEGIVERR = 90;
        public static final int HPLIFEGIVERW = 120;

        public static final int HPSMALLANGELK = 10;
        public static final int HPSMALLANGELP = 15;
        public static final int HPSMALLANGELR = 20;
        public static final int HPSMALLANGELW = 25;

        public static final int HPSPAWNERK = 200;
        public static final int HPSPAWNERP = 150;
        public static final int HPSPAWNERR = 180;
        public static final int HPSPAWNERW = 120;
    }

    public static class AssetDamageAngels {
        public static final float DAMAGEDAMAGEANGELK = 1.15f;
        public static final float DAMAGEDAMAGEANGELP = 1.2f;
        public static final float DAMAGEDAMAGEANGELR = 1.3f;
        public static final float DAMAGEDAMAGEANGELW = 1.4f;

        public static final float DAMAGEDRACULAK = 0.8f;
        public static final float DAMAGEDRACULAP = 0.7f;
        public static final float DAMAGEDRACULAR = 0.9f;
        public static final float DAMAGEDRACULAW = 0.6f;

        public static final float DAMAGEGOODBOYK = 1.4f;
        public static final float DAMAGEGOODBOYP = 1.5f;
        public static final float DAMAGEGOODBOYR = 1.4f;
        public static final float DAMAGEGOODBOYW = 1.3f;

        public static final float DAMAGESMALLANGELK = 1.1f;
        public static final float DAMAGESMALLANGELP = 1.15f;
        public static final float DAMAGESMALLANGELR = 1.05f;
        public static final float DAMAGESMALLANGELW = 1.1f;
    }

    public AngelsFactory() {
        new AssetDamageAngels();
        new AssetHpAngels();
    }

    public static AngelsFactory getAngelInstance() {
        if(instance == null) {
            instance = new AngelsFactory();
        }
        return instance;
    }

    public final Angels getAngelsByType(final String type,
               final int line, final int column, final int round ) {
        if (type.equals("DamageAngel")) {
            return new Angels(type,"helped", round, line, column, 0, 0 ,
                    0 ,0 , AssetDamageAngels.DAMAGEDAMAGEANGELK,
                    AssetDamageAngels.DAMAGEDAMAGEANGELP, AssetDamageAngels.
                    DAMAGEDAMAGEANGELR, AssetDamageAngels.DAMAGEDAMAGEANGELW);
        } else if (type.equals("DarkAngel")) {
            return new Angels(type,"hit", round, line, column, AssetHpAngels.
                    HPDARKANGELK, AssetHpAngels.HPDARKANGELP, AssetHpAngels.
                    HPDARKANGELR, AssetHpAngels.HPDARKANGELW, 0,
                    0 ,0 ,0);
        } else if (type.equals("Dracula")) {
            return new Angels(type,"hit", round, line, column, AssetHpAngels.
                    HPDRACULAK, AssetHpAngels.HPDRACULAP, AssetHpAngels.
                    HPDRACULAR, AssetHpAngels.HPDRACULAW, AssetDamageAngels.
                    DAMAGEDRACULAK, AssetDamageAngels.DAMAGEDRACULAP,
                    AssetDamageAngels.DAMAGEDRACULAR, AssetDamageAngels.DAMAGEDRACULAW);
        } else if (type.equals("GoodBoy")) {
            return new Angels(type,"helped", round, line, column, AssetHpAngels.
                    HPGOODBOYK, AssetHpAngels.HPGOODBOYP, AssetHpAngels.
                    HPGOODBOYR, AssetHpAngels.HPGOODBOYW, AssetDamageAngels.
                    DAMAGEGOODBOYK, AssetDamageAngels.DAMAGEGOODBOYP,
                    AssetDamageAngels.DAMAGEGOODBOYR, AssetDamageAngels.DAMAGEGOODBOYW);
        } else if (type.equals("LevelUpAngel")) {
            return new LevelUpAngel(type,"helped", round, line, column);
        }  else if (type.equals("LifeGiver")) {
            return new Angels(type,"helped", round, line, column, AssetHpAngels.
                    HPLIFEGIVERK, AssetHpAngels.HPLIFEGIVERP, AssetHpAngels.
                    HPLIFEGIVERR, AssetHpAngels.HPLIFEGIVERW, 0,
                    0 ,0 ,0);
        } else if (type.equals("SmallAngel")) {
            return new Angels(type, "helped", round, line, column, AssetHpAngels.
                    HPSMALLANGELK, AssetHpAngels.HPSMALLANGELP, AssetHpAngels.
                    HPSMALLANGELR, AssetHpAngels.HPSMALLANGELW, AssetDamageAngels.
                    DAMAGESMALLANGELK, AssetDamageAngels.DAMAGESMALLANGELP,
                    AssetDamageAngels.DAMAGESMALLANGELR, AssetDamageAngels.DAMAGESMALLANGELW);
        } else if (type.equals("Spawner")) {
            return new Spawner(type, "helped", round, line, column);
        } else if (type.equals("TheDoomer")) {
            return new TheDoomer(type,"hit", round, line, column);
        }
        return new XPAngel(type, "hit", round, line, column);
    }

}
