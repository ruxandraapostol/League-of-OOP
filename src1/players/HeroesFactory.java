package players;

public class HeroesFactory {
    private static HeroesFactory instance = null;

    private static class AssetsHitPoints {
        public static final int PYROMANHP = 500;
        public static final int KNIGHTHP = 900;
        private static final int WIZARDHP = 400;
        private static final int ROGUEHP = 600;
    }

    private static class AssetsBonusHP {
        public static final int PYROMANBHP = 50;
        public static final int KNIGHTBHP = 80;
        private static final int WIZARDBHP = 30;
        private static final int ROGUEBHP = 40;
    }

    public HeroesFactory() {
        new AssetsBonusHP();
        new AssetsHitPoints();
    }

    public static HeroesFactory getInstance() {
        if (instance == null) {
            instance = new HeroesFactory();
        }
        return instance;
    }

    public final Heroes getHeroesByLetter(final String s) {
        if (s.equals("P")) {
            return new Pyromancer(AssetsHitPoints.PYROMANHP,
                    AssetsBonusHP.PYROMANBHP, 0, 0, "P");
        } else if (s.equals("W")) {
            return new Wizard(AssetsHitPoints.WIZARDHP,
                    AssetsBonusHP.WIZARDBHP, 0, 0, "W");
        } else if (s.equals("R")) {
            return new Rogue(AssetsHitPoints.ROGUEHP,
                    AssetsBonusHP.ROGUEBHP, 0, 0, "R");
        }
        return new Knight(AssetsHitPoints.KNIGHTHP,
                AssetsBonusHP.KNIGHTBHP, 0, 0, "K");
    }

}
