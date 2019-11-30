package players;

public class Heroes {
    private String letter;
    private int hitPoints;
    private int bonusHitPoints;
    private int experiencePoints;
    private int level;

    public Heroes(final int hitPoints, final int bonusHitPoints,
                  final int experiencePoints, final int level, final String letter){
        this.letter = letter;
        this.level = level;
        this.bonusHitPoints = bonusHitPoints;
        this.hitPoints = hitPoints;
        this.experiencePoints = experiencePoints;
    }

    public int totalDamage(){
        return 0;
    }

    public final int getExperiencePoints() {
        return experiencePoints;
    }

    public final int getHitPoints() {
        return hitPoints;
    }

    public final int getLevel(){ return level; }

    public void setExperiencePoints(final int loserLevel){
        int x = 200 - (level - loserLevel) * 40;
        this.experiencePoints += Math.max(0, x);
        if (this.experiencePoints >= 250 + 50 * this.level){
            //noul nivel
            int newLevel = (this.experiencePoints - 250) / 50 + 1;
            //puntctele hp se redau
            setHitPoints(this.hitPoints + bonusHitPoints * (newLevel - this.level));
            this.level = newLevel;
        }
    }

    public final String getLetter() {
        return this.letter;
    }

    public void setHitPoints(final int hitPoints){
        this.hitPoints = hitPoints;
    }

    public void setBonusHitPoints(int bhp) {
        this.bonusHitPoints = bhp;
    }
}
