package angels;

import players.*;

public class Angels {
    private int round;
    private int line;
    private int column;
    private String type;
    private String predicate;

    private int hpK;
    private int hpP;
    private int hpR;
    private int hpW;

    private float damageK;
    private float damageP;
    private float damageR;
    private float damageW;

    public Angels (final String type,final String predicate, final int round, final int
            line, final int column, final int hpK, final int hpP,
            final int hpR, final int hpW, final float damageK,
            final float damageP, final float damageR, final float damageW) {
        this.type = type;
        this.predicate = predicate;
        this.column = column;
        this.round = round;
        this.line = line;

        this.hpK = hpK;
        this.hpP = hpP;
        this.hpR = hpR;
        this.hpW = hpW;

        this.damageK = damageK;
        this.damageP = damageP;
        this.damageR = damageR;
        this.damageW = damageW;
    }

    public Angels (final String type,final String predicate, final int round, final int
            line, final int column) {
        this.type = type;
        this.predicate = predicate;
        this.column = column;
        this.round = round;
        this.line = line;
    }

    public void angelPower(Heroes hero, int newHP, float angelDamage){
        if (hero.getHitPoints() <= 0){
            return;
        }
        hero.setHitPoints(hero.getHitPoints() + newHP);
        hero.setAngelsModifyer(angelDamage);
    }

    public void angelPlay(Knight knight) {
        angelPower(knight, this.hpK, this.damageK);
    }

    public void angelPlay(Pyromancer pyromancer) {
        angelPower(pyromancer, this.hpP, this.damageP);
    }

    public void angelPlay(Wizard wizard) {
        angelPower(wizard, this.hpW, this.damageW);
    }

    public void angelPlay(Rogue rogue) {
        angelPower(rogue, this.hpR, this.damageR);
    }

    public void angelPlay(Heroes hero)  { }

    public final int getRound() {
        return this.round;
    }

    public final int getLine() {
        return this.line;
    }

    public final int getColumn() {
        return this.column;
    }

    public final String getType() { return this.type; }

    public final String getPredicate() { return this.predicate; }
}
