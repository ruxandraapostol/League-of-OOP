package main;

import map.Map;
import java.util.ArrayList;

public final class Main {
    private Main() {
        throw new AssertionError("Error");
    }

    public static void main(final String[] args) {
        Input input = new Input(args[0], args[1]);
        GameInput gameInput = input.load();
        ArrayList<Map> playersMap = new ArrayList<>();

        //asociez fiecarui jucator o linie si o coloana
        for (int i = 0; i < gameInput.getP(); i++) {
            playersMap.add(new Map(gameInput.getPlayers()[i], i));
        }

        //pentru fiecare runda
        for (int i = 0; i < gameInput.getR(); i++) {
            //fiecare jucator este mutat
            for (int j = 0; j < gameInput.getP(); j++) {
                playersMap.get(j).move(gameInput.getMove().get(i).charAt(j));
            }

            //aplic dot fiecarui jucator, daca nu are va fi egal cu 0
            for (Map player1 : playersMap) {
                player1.getHero().doT();
            }

            // pentru fiecare jucator viu
            for (Map player1 : playersMap) {
                if (player1.getHero().getHitPoints() <= 0) {
                    continue;
                }

                String pos = "" + gameInput.getMap().
                        get(player1.getLine()).charAt(player1.getColumn());
                //verific daca ceilalti jucatori vii si pozitionati dupa el
                for (Map player2 : playersMap) {
                    if (player1.getHero().getId() <= player2.getHero().getId()) {
                        continue;
                    }
                    if (player2.getHero().getHitPoints() <= 0) {
                        continue;
                    }
                    //se afla pe aceeasi pozitie cu el
                    if (player1.getColumn() == player2.getColumn()
                            && player1.getLine() == player2.getLine()) {

                        //se realizeaza interactiunea, bataia
                        player1.getHero().accept(player2.getHero(), pos);
                        player2.getHero().accept(player1.getHero(), pos);

                        //daca vreunul din ei a murit i se dau punctele de experienta
                        //celuilalt, respectiv nivelul, dupa caz
                        if (player1.getHero().getHitPoints() <= 0) {
                            player1.getHero().setHitPoints(0);
                            player2.getHero().setExperiencePoints(player1.getHero().getLevel());
                        }

                        if (player2.getHero().getHitPoints() <= 0) {
                            player2.getHero().setHitPoints(0);
                            player1.getHero().setExperiencePoints(player2.getHero().getLevel());
                        }
                        break;
                    }
                }
            }
        }

        //pentru fiecare jucator creez out-ul
        String output = "";
        for (Map player : playersMap) {
            output += player.getHero().getLetter() + " ";
            if (player.getHero().getHitPoints() <= 0) {
                output += "dead";
            } else {
                output += player.getHero().getLevel() + " " + player.getHero().
                        getExperiencePoints() + " " + player.getHero().getHitPoints()
                        + " " + player.getLine() + " " + player.getColumn();
            }
            output += "\n";
        }
        input.write(output);
    }
}

