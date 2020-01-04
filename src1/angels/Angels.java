package angels;

import players.Heroes;
import players.Rogue;
import players.Knight;
import players.Wizard;
import players.Pyromancer;

public class Angels {
    private int round;
    private int line;
    private int column;
    private String type;
    private String predicate;

    private int hpK = 0;
    private int hpP = 0;
    private int hpR = 0;
    private int hpW = 0;

    private float damageK;
    private float damageP;
    private float damageR;
    private float damageW;

    public Angels(final String type, final String predicate, final int round, final int
            line, final int column) {
        this.type = type;
        this.predicate = predicate;
        this.column = column;
        this.round = round;
        this.line = line;
    }

    public Angels(final String type, final String predicate, final int round, final int
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


    public final void angelPower(final Heroes hero, final int newHP, final float angelDamage) {
        if (hero.getHitPoints() <= 0) {
            return;
        }
        hero.setHitPoints(hero.getHitPoints() + newHP);
        hero.setAngelsModifyer(hero.getAngelsModifyer() + angelDamage);
    }

    /**
     * In functie de jucatorul cu care interactioneaza
     * ingerul foloseste anumiti modificatori.
     * @param knight - modificatorii de knight
     */
    public void angelPlay(final Knight knight) {
        angelPower(knight, this.hpK, this.damageK);
    }

    /**
     * In functie de jucatorul cu care interactioneaza
     * ingerul foloseste anumiti modificatori.
     * @param pyromancer - modificatorii de pyromancer
     */
    public void angelPlay(final Pyromancer pyromancer) {
        angelPower(pyromancer, this.hpP, this.damageP);
    }
    /**
     * In functie de jucatorul cu care interactioneaza
     * ingerul foloseste anumiti modificatori.
     * @param wizard - modificator de wizard
     */
    public void angelPlay(final Wizard wizard) {
        angelPower(wizard, this.hpW, this.damageW);
    }

    /**
     * In functie de jucatorul cu care interactioneaza
     * ingerul foloseste anumiti modificatori.
     * @param rogue - modificatorii de rogue
     */
    public void angelPlay(final Rogue rogue) {
        angelPower(rogue, this.hpR, this.damageR);
    }

    public void angelPlay(final Heroes hero)  { }

    public final int getRound() {
        return this.round;
    }

    public final int getLine() {
        return this.line;
    }

    public final int getColumn() {
        return this.column;
    }

    public final String getType() {
        return this.type;
    }

    public final String getPredicate() {
        return this.predicate;
    }
}
