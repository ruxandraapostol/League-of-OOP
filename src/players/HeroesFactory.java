package players;

import java.util.HashMap;
import java.util.Map;

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

    private final Map<String, Heroes> heroesByLetter;

    public HeroesFactory() {
        heroesByLetter = new HashMap<String, Heroes>();
        new AssetsBonusHP();
        new AssetsHitPoints();
    }

    private void initHeroes() {
        Heroes pyromancer = new Pyromancer(AssetsHitPoints.PYROMANHP,
                AssetsBonusHP.PYROMANBHP, 0, 0, "P");
        Heroes knight = new Knight(AssetsHitPoints.KNIGHTHP,
                AssetsBonusHP.KNIGHTBHP, 0, 0, "K");
        Heroes wizard = new Wizard(AssetsHitPoints.WIZARDHP,
                AssetsBonusHP.WIZARDBHP, 0, 0, "W");
        Heroes rogue = new Rogue(AssetsHitPoints.ROGUEHP,
                AssetsBonusHP.ROGUEBHP, 0, 0, "R");

        // insert in hashMap
        heroesByLetter.put("P", pyromancer);
        heroesByLetter.put("K", knight);
        heroesByLetter.put("W", wizard);
        heroesByLetter.put("R", rogue);
    }

    public static HeroesFactory getInstance() {
        if (instance == null) {
            instance = new HeroesFactory();
        }
        return instance;
    }

    public Heroes getHeroesByLetter(final String s) {
        initHeroes();
        return heroesByLetter.get(s);
    }



}
