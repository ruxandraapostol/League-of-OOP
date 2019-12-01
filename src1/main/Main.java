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

        for (int i = 0; i < gameInput.getP(); i++) {
            playersMap.add(new Map(gameInput.getPlayers()[i], i));
        }

        for (int i = 0; i < gameInput.getR(); i++) {
            for (int j = 0; j < gameInput.getP(); j++) {
                playersMap.get(j).move(gameInput.getMove().get(i).charAt(j));
            }

            for (Map player1 : playersMap) {
                player1.getHero().doT();
            }

            for (Map player1 : playersMap){
                if(player1.getHero().getHitPoints() <= 0){
                    continue;
                }
                if(player1.getLine() >= gameInput.getN() || player1.getColumn() >= gameInput.getM()
                    || player1.getLine() < 0 || player1.getColumn() < 0){
                    continue;
                }

                String pos = "" + gameInput.getMap().get(player1.getLine()).charAt(player1.getColumn());
                for (Map player2 : playersMap){
                    if(player1.getHero().getId() <= player2.getHero().getId()){
                        continue;
                    }
                    if(player2.getHero().getHitPoints() <= 0) {
                        continue;
                    }
                    if(player1.getHero().getId() != player2.getHero().getId()
                            && player2.getHero().getHitPoints() > 0 &&
                            player1.getHero().getHitPoints() > 0 &&
                            player1.getColumn() == player2.getColumn() &&
                            player1.getLine() == player2.getLine()){

                        if( player2.getHero().getId() == 4 && i == 10){
                            System.out.println(player2.getHero().getHitPoints());
                        }

                        player1.getHero().accept(player2.getHero(), pos);
                        player2.getHero().accept(player1.getHero(), pos);

                        if (player1.getHero().getHitPoints() <= 0){
                            player1.getHero().setHitPoints(0);
                            player2.getHero().setExperiencePoints(player1.getHero().getLevel());
                        }

                        if (player2.getHero().getHitPoints() <= 0){
                            player2.getHero().setHitPoints(0);
                            player1.getHero().setExperiencePoints(player2.getHero().getLevel());
                        }
                        break;
                    }
                }
            }
            /*System.out.println("Round: " + i);
            for(Map player : playersMap){
                System.out.print(player.getHero().getLetter()+ " ");
                if(player.getHero().getHitPoints() <= 0){
                    System.out.println("dead");
                } else {
                    System.out.println(player.getHero().getLevel() + " "
                            + player.getHero().getExperiencePoints() + " "
                            + player.getHero().getHitPoints() + " "
                            + player.getLine() + " " + player.getColumn());
                }
            }
            System.out.println("-----------END ROUND--------");*/
        }

        String s = "";
        for(Map player : playersMap) {
            s += player.getHero().getLetter() + " ";
            if (player.getHero().getHitPoints() <= 0) {
                s += "dead";
            } else {
                s += player.getHero().getLevel() + " " +
                        player.getHero().getExperiencePoints() + " "
                        + player.getHero().getHitPoints() + " "
                        + player.getLine() + " " + player.getColumn();
            }
            s += "\n";
        }
        input.write(s);
    }
}

/*
            System.out.println("Round: " + i);
            for(Map player : playersMap){
                System.out.print(player.getHero().getLetter()+ " ");
                if(player.getHero().getHitPoints() <= 0){
                    System.out.println("dead");
                } else {
                    System.out.println(player.getHero().getLevel() + " "
                            + player.getHero().getExperiencePoints() + " "
                            + player.getHero().getHitPoints() + " "
                            + player.getLine() + " " + player.getColumn());
                }
            }
            System.out.println("-----------END ROUND--------");*/