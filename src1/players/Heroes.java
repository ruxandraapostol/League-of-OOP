package players;

import angels.Angels;

public class Heroes {
    private int id = 0; //pentru a nu se batea un jucator cu el insusi
    private String letter;  //sa imi fie mai usor sa identific tipul jucatorului
    private int hitPoints;
    private int bonusHitPoints;
    private int experiencePoints;
    private int level;
    private long dot1 = 0;
    private int index1 = 0; //numarul de runde pentru aplicarea dot-ului
    private int paralyzed = 0;  //numarul de runde pentru incapacitate
    private float angelsModifyer = 1;
    private float strategy = 1;

    public Heroes(final int hitPoints, final int bonusHitPoints,
                  final int experiencePoints, final int level, final String letter) {
        this.letter = letter;
        this.level = level;
        this.bonusHitPoints = bonusHitPoints;
        this.hitPoints = hitPoints;
        this.experiencePoints = experiencePoints;
    }

    /**
     * Aceasta metoda permite obiectelor instantiate cu clase
     * derivate ale acesteia sa accepte interactiunea uneia
     * cu cealalta. Astfel pot utiliza conceptul de double dispatch.
     * @param h reprezinta obiectul cu care va interactiona
     * @param s reprezinta suprafata/ terenul pe care va avea loc
     *          batalia
     */
    public void accept(final Heroes h, final String s) {
        h.fight(this, s);
    }

    //interactiunea propriu-zisa cu celelalte obiecte
    public void fight(final Knight h, final String s) { }
    public void fight(final Pyromancer h, final String s) { }
    public void fight(final Rogue h, final String s) { }
    public void fight(final Wizard h, final String s) { }
    public void fight(final Heroes h, final String s) { }

    public void acceptAngel (final Angels angels) {
        angels.angelPlay(this);
    }


    /**
     * Aceasta metoda va fi rescrisa in clasele derivate.
     * Este necesara pentru a calcula "viata" din care
     * va rezulta damage-ul dat de deflect.
     * @param s indica suprafata pe care va fi batalia
     * @return se va returna damage-ul dat fara modificatori
     * de rasa
     */
    public int totalDamage(final String s) {
        return 0;
    }

    // scad dot-ul din viata si actualizez numarul de runde
    public final void doT() {
        if (index1 > 0) {
            hitPoints -= dot1;
            index1--;
        }
    }

    public final void setDot1(final long dot, final int index) {
        dot1 = dot;
        index1 = index;
    }

    public final int getParalyzed() {
        return paralyzed;
    }

    public final int getExperiencePoints() {
        return experiencePoints;
    }

    public final int getHitPoints() {
        return hitPoints;
    }

    public final int getLevel() {
        return level;
    }

    public final int getId() {
        return id;
    }

    public final float getAngelsModifyer()  {
        return angelsModifyer;
    }

    public final int getBonusHitPoints() {
        return bonusHitPoints;
    }

    public final String getLetter() {
        return this.letter;
    }

    public final float getStrategy() { return  this.strategy; }

    public final void setStrategy(final float strategy) { this.strategy = strategy; }

    public final void setId(final int id) {
        this.id = id;
    }

    public final void setAngelsModifyer(final float angelsModifyer) {
        this.angelsModifyer = angelsModifyer;
    }

    public static class Constants {
        public static final int TWOHUNDRED = 200;
        public static final int TWOHUNDREDFIFTY = 250;
        public static final int FIFTY = 50;
        public static final int FORTY = 40;
    }

    // setez punctele de experienta dupa formula
    public final void setExperiencePoints(final int loserLevel) {
        int x = Constants.TWOHUNDRED - (level - loserLevel) * Constants.FORTY;
        this.experiencePoints += Math.max(0, x);
        if (this.experiencePoints >= Constants.TWOHUNDREDFIFTY
                + Constants.FIFTY * this.level && this.hitPoints > 0) {
            //noul nivel
            this.level = (this.experiencePoints - Constants.TWOHUNDREDFIFTY) / Constants.FIFTY + 1;
            //puntctele hp se redau
            setHitPoints(HeroesFactory.getInstance().getHeroesByLetter(
                    this.getLetter()).getHitPoints() + bonusHitPoints * this.level);
        }
    }

    public final void setXPByAngel (final int xpByAngel) {
        this.experiencePoints = xpByAngel;
        if (this.experiencePoints >= Constants.TWOHUNDREDFIFTY
                + Constants.FIFTY * this.level && this.hitPoints > 0) {
            //noul nivel
            this.level = (this.experiencePoints - Constants.TWOHUNDREDFIFTY) / Constants.FIFTY + 1;
            //puntctele hp se redau
            setHitPoints(HeroesFactory.getInstance().getHeroesByLetter(
                    this.getLetter()).getHitPoints() + bonusHitPoints * this.level);
        }
    }

    public final void setParalyzed(final int paralyzed) {
        this.paralyzed = paralyzed;
    }

    public final void setHitPoints(final int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
