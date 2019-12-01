package players;

public class Heroes {
    private int id = 0;
    private String letter;
    private int hitPoints;
    private int bonusHitPoints;
    private int experiencePoints;
    private int level;
    private long dot1 = 0;
    private int index1 = 0;

    public Heroes(final int hitPoints, final int bonusHitPoints,
                  final int experiencePoints, final int level, final String letter){
        this.letter = letter;
        this.level = level;
        this.bonusHitPoints = bonusHitPoints;
        this.hitPoints = hitPoints;
        this.experiencePoints = experiencePoints;
    }

    public void accept(Heroes h, String s) {
        h.fight(this, s);
    }
    public void fight(Knight h, String s) {}
    public void fight(Pyromancer h, String s) {}
    public void fight(Rogue h, String s) {}
    public void fight(Wizard h, String s) {}
    public void fight(Heroes h, String s) {}

    public int totalDamage( String s){
        return 0;
    }

    public void doT() {
        if(index1 > 0){
            hitPoints -= dot1;
            index1 --;
        }
    }

    public void setDot1(long dot, int index) {
        dot1 = dot;
        index1 = index;
    }

    public final int getExperiencePoints() {
        return experiencePoints;
    }

    public final int getHitPoints() {
        return hitPoints;
    }

    public final int getLevel() { return level; }

    public final int getId() { return id; }

    public final int getBonusHitPoints() {
        return bonusHitPoints;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExperiencePoints(final int loserLevel){
        int x = 200 - (level - loserLevel) * 40;
        this.experiencePoints += Math.max(0, x);
        if (this.experiencePoints >= 250 + 50 * this.level){
            //noul nivel
            this.level = (this.experiencePoints - 250) / 50 + 1;
            System.out.println("punctele bonus sunt :" + bonusHitPoints);
            System.out.println("punctele standard sunt:" + HeroesFactory.getInstance().getHeroesByLetter
                    (this.getLetter()).getHitPoints() );
            //puntctele hp se redau
            setHitPoints(HeroesFactory.getInstance().getHeroesByLetter
                    (this.getLetter()).getHitPoints() + bonusHitPoints * this.level);
        }
    }

    public final String getLetter() {
        return this.letter;
    }

    public void setHitPoints(final int hitPoints){
        this.hitPoints = hitPoints;
    }
}
