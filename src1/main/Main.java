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
            for (Map player1 : playersMap){
                boolean ok = true;
                if(player1.getHero().getHitPoints() == 0){
                    continue;
                }
                String pos = "" + gameInput.getMap().get(player1.getLine()).charAt(player1.getColumn());
                for (Map player2 : playersMap){
                    player2.getHero().doT();
                    if(player1.getHero().getId() != player2.getHero().getId()
                            && player2.getHero().getHitPoints()!=0 &&
                            player1.getColumn() == player2.getColumn() &&
                            player1.getLine() == player2.getLine()){
                        player1.getHero().accept(player2.getHero(), pos);
                        player2.getHero().accept(player1.getHero(), pos);
                        ok = false;
                        break;
                    }
                    if(player1.getHero().getId() == player2.getHero().getId()
                            && player2.getHero().getHitPoints()==0){
                        ok = false;
                    }
                }
                if (!ok) {
                    break;
                }
            }
            System.out.println("Runda " + i);
            System.out.println("-------------------------------------");
            for(Map player : playersMap){
                System.out.print(player.getHero().getLetter()+ " ");
                if(player.getHero().getHitPoints() == 0){
                    System.out.println("dead");
                } else {
                    System.out.println(player.getHero().getLevel() + " "
                            + player.getHero().getHitPoints() + " "
                            + player.getHero().getExperiencePoints() + " "
                            + player.getLine() + " " + player.getColumn());
                }
            }
        }

        String s = "";
        for(Map player : playersMap) {
            s += player.getHero().getLetter() + " ";
            if (player.getHero().getHitPoints() == 0) {
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
