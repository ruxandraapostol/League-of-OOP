package main;

import angels.Angels;
import greatwizard.GreatWizard;
import greatwizard.ObserverGreatWizard;
import map.Map;
import players.AssociationLetterType;

import java.util.ArrayList;

public final class Main {
    private Main() {
        throw new AssertionError("Error");
    }

    public static void main(final String[] args) {
        Input input = new Input(args[0], args[1]);
        GameInput gameInput = input.load();
        ArrayList<Map> playersMap = new ArrayList<>();
        GreatWizard greatWizard = new GreatWizard("");
        ObserverGreatWizard observerGreatWizard = new ObserverGreatWizard(greatWizard);
        AssociationLetterType association = new AssociationLetterType();
        String output = "";

        //asociez fiecarui jucator o linie si o coloana
        for (int i = 0; i < gameInput.getP(); i++) {
            playersMap.add(new Map(gameInput.getPlayers()[i], i));
        }

        //pentru fiecare runda
        for (int i = 0; i < gameInput.getR(); i++) {
            output = "~~ Round " + (i + 1) + " ~~";
            greatWizard.setValue(output);
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
                        int level1 = player1.getHero().getLevel();
                        int level2 = player2.getHero().getLevel();
                        int copyHP = player1.getHero().getHitPoints();
                        player1.getHero().accept(player2.getHero(), pos);
                        int copyHPafterFight = copyHP - player1.getHero().getHitPoints();
                        player1.getHero().setHitPoints(copyHP);
                        player2.getHero().accept(player1.getHero(), pos);
                        player1.getHero().setHitPoints(player1.getHero().getHitPoints() - copyHPafterFight);


                        //daca vreunul din ei a murit i se dau punctele de experienta
                        //celuilalt, respectiv nivelul, dupa caz
                        if (player1.getHero().getHitPoints() <= 0) {
                            player1.getHero().setHitPoints(0);
                            player2.getHero().setExperiencePoints(player1.getHero().getLevel());
                            output = "Player " + association.wordByLetter(player1.getHero().getLetter())
                                    + " " + player1.getHero().getId() + " was killed by "
                                    + association.wordByLetter(player2.getHero().getLetter())
                                    + " " + player2.getHero().getId();
                            greatWizard.setValue(output);
                            for(int k = level2; k < player2.getHero().getLevel(); k++){
                                output = association.wordByLetter(player2.getHero().getLetter())
                                        + " " + player2.getHero().getId() + " reached level " + (k + 1);
                                greatWizard.setValue(output);
                            }
                        }

                        if (player2.getHero().getHitPoints() <= 0) {
                            player2.getHero().setHitPoints(0);
                            player1.getHero().setExperiencePoints(player2.getHero().getLevel());
                            output = "Player " + association.wordByLetter(player2.getHero().getLetter())
                                    + " " + player2.getHero().getId() + " was killed by "
                                    + association.wordByLetter(player1.getHero().getLetter())
                                    + " " + player1.getHero().getId();
                            greatWizard.setValue(output);
                            for(int k = level1; k < player1.getHero().getLevel(); k++){
                                output = association.wordByLetter(player1.getHero().getLetter())
                                        + " " + player1.getHero().getId() + " reached level " + (k + 1);
                                greatWizard.setValue(output);
                            }
                        }
                        break;
                    }
                }
            }
           /* output = "~~ Results before angel ~~" + "\n";
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
            System.out.println(output);*/
            for (Angels angel : gameInput.getAngels()){
                if (angel.getRound() != i){
                    continue;
                } else {
                    output = "Angel " + angel.getType() + " was spawned at " + angel.getLine() + " " + angel.getColumn();
                    greatWizard.setValue(output);
                    for (Map player : playersMap) {
                        if (angel.getLine() != player.getLine()) {
                            continue;
                        }
                        if (player.getHero().getHitPoints() <= 0 && !angel.getType().equals("Spawer")) {
                            continue;
                        }
                        int level = player.getHero().getLevel();
                        player.getHero().acceptAngel(angel);
                        output = angel.getType() + " " + angel.getPredicate() + " "
                                + association.wordByLetter(player.getHero().getLetter())
                                + " " + player.getHero().getId();
                        greatWizard.setValue(output);
                        for (int k = level; k < player.getHero().getLevel(); k++) {
                            output = association.wordByLetter(player.getHero().getLetter())
                                    + " " + player.getHero().getId() + " reached level " + (k + 1);
                            greatWizard.setValue(output);
                        }
                        if (player.getHero().getHitPoints() <= 0) {
                            output = "Player " + association.wordByLetter(player.getHero().getLetter())
                                    + " " + player.getHero().getId() + " was killed by an angel";
                            greatWizard.setValue(output);
                        }
                    }
                }
            }
            greatWizard.setValue("");
            output = "~~ Results after angel ~~" + "\n";
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
            System.out.println(output);
        }

        //pentru fiecare jucator creez out-ul
        output = "~~ Results ~~" + "\n";
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
        greatWizard.setValue(output);
        input.write(observerGreatWizard.getMessage());
        System.out.println(observerGreatWizard.getMessage());
    }
}

