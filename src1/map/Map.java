package map;

import players.Heroes;
import players.HeroesFactory;

public class Map {
    private Heroes hero;
    private int line;
    private int column;

    // asociez fiecarui jucator, linia si coloana pe care se afla
    public Map(final String[] players, final int index) {
        hero = HeroesFactory.getInstance().getHeroesByLetter(players[0]);
        hero.setId(index);
        line = Integer.parseInt(players[1]);
        column = Integer.parseInt(players[2]);
    }

    public final Heroes getHero() {
        return hero;
    }

    public final int getColumn() {
        return column;
    }

    public final int getLine() {
        return line;
    }

    public final void move(final char move) {
        //mutarile see fac doar daca jucatorul nu e in incapacitate
        if (hero.getParalyzed() != 0) {
            hero.setParalyzed(hero.getParalyzed() - 1);
            return;
        }
        //mutarile propriu-zise
        if (move == 'L') {
            column--;
        } else if (move == 'R') {
            column++;
        } else if (move == 'U') {
            line--;
        } else if (move == 'D') {
            line++;
        }
    }

}
