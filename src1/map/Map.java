package map;

import players.Heroes;
import players.HeroesFactory;

public class Map {
    private Heroes hero;
    private int line;
    private int column;

    public Map(String[] players, int index){
        hero = HeroesFactory.getInstance().getHeroesByLetter(players[0]);
        hero.setId(index);
        line = Integer.parseInt(players[1]);
        column = Integer.parseInt(players[2]);
    }

    public Heroes getHero() {
        return hero;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    public void move(char move) {
        if(hero.getParalyzed() != 0){
            hero.setParalyzed(hero.getParalyzed() - 1);
            return;
        }
        if(move == 'L') {
            column --;
        } else if(move == 'R') {
            column ++;
        } else if(move == 'U') {
            line --;
        } else if(move == 'D') {
            line ++;
        }
    }

}
