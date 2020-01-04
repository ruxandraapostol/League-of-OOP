package main;

import angels.Angels;
import greatwizard.GreatWizard;
import greatwizard.ObserverGreatWizard;
import map.HeroOnMap;
import players.AssociationLetterType;

import java.util.ArrayList;

public class Play {
    Input input;
    GameInput gameInput;
    ArrayList<HeroOnMap> playersMap = new ArrayList<>();
    GreatWizard greatWizard = new GreatWizard("");
    ObserverGreatWizard observerGreatWizard = new ObserverGreatWizard(greatWizard);
    AssociationLetterType association = new AssociationLetterType();
    String output = "";

    public Play(Input input) {
        this.input = input;
        gameInput = input.load();
        //asociez fiecarui jucator o pozitie pe harta
        for (int i = 0; i < gameInput.getP(); i++) {
            playersMap.add(new HeroOnMap(gameInput.getPlayers()[i], i));
        }
    }

    public final void beforeTheRound(final int i) {
        output = "~~ Round " + (i + 1) + " ~~";
        greatWizard.setValue(output);
        //fiecare jucator este mutat
        for (int j = 0; j < gameInput.getP(); j++) {
            playersMap.get(j).move(gameInput.getMove().get(i).charAt(j));
        }

        //aplic dot fiecarui jucator, daca nu are va fi egal cu 0
        for (HeroOnMap player1 : playersMap) {
            player1.getHero().doT();
            player1.getHero().chooseStrategy();
        }
    }

    //afisare mesaj de omorat sau readus la viata de un inger
    private final void message (final HeroOnMap player, final String message){
        output = "Player " + association.wordByLetter(player.getHero().getLetter())
                + " " + player.getHero().getId() + message;
        greatWizard.setValue(output);
    }

    //afisare mesaj de crestere in nivel
    private final void nextLevel(final int oldLevel, final HeroOnMap player){
        for (int k = oldLevel; k < player.getHero().getLevel(); k++) {
            output = association.wordByLetter(player.getHero().getLetter())
                    + " " + player.getHero().getId() + " reached level " + (k + 1);
            greatWizard.setValue(output);
        }
    }

    private final void deadPlayer (final HeroOnMap player1,
            final HeroOnMap player2, final int level) {
        //daca un jucator a fost omorat
        if (player1.getHero().getHitPoints() <= 0) {
            player1.getHero().setHitPoints(0);
            //dau xp-ul si cresc nivelu daca este cazul
            player2.getHero().setExperiencePoints(player1.getHero().getLevel());
            //afisez mesajul
            output = "Player " + association.wordByLetter(player1.getHero().getLetter())
                    + " " + player1.getHero().getId() + " was killed by "
                    + association.wordByLetter(player2.getHero().getLetter())
                    + " " + player2.getHero().getId();
            greatWizard.setValue(output);
            //afisez mesajul de crestere in nivel
            this.nextLevel(level, player2);
        }
    }

    public void fight() {
        //pentru fiecare jucator
        for (HeroOnMap player1 : playersMap) {
            //viu
            if (player1.getHero().getHitPoints() <= 0) {
                continue;
            }
            //si care nu a iesit de pe harta
            if (player1.getLine() >= gameInput.getN() || player1.getLine() < 0
                    || player1.getColumn() >= gameInput.getM() || player1.getColumn() < 0) {
                continue;
            }
            String pos = "" + gameInput.getMap().
                    get(player1.getLine()).charAt(player1.getColumn());
            //verific daca ceilalti jucatori vii si pozitionati dupa el
            //ca sa nu se bata doi de doua ori
            for (HeroOnMap player2 : playersMap) {
                if (player1.getHero().getId() <= player2.getHero().getId()
                        || player2.getHero().getHitPoints() <= 0) {
                    continue;
                }
                //se afla pe aceeasi pozitie cu el
                if (player1.getColumn() == player2.getColumn()
                        && player1.getLine() == player2.getLine()) {

                    //se realizeaza interactiunea, bataia
                    // ma asigur ca nu se incurca hp-ul din batalie cu
                    // cel de dupa alegerea strategiei
                    int level1 = player1.getHero().getLevel();
                    int level2 = player2.getHero().getLevel();
                    int copyHP = player1.getHero().getHitPoints();
                    player1.getHero().accept(player2.getHero(), pos);
                    int copyHPafterFight = copyHP - player1.getHero().getHitPoints();
                    player1.getHero().setHitPoints(copyHP);
                    player2.getHero().accept(player1.getHero(), pos);
                    player1.getHero().setHitPoints(player1.getHero().
                            getHitPoints() - copyHPafterFight);

                    //daca vreunul din ei a murit i se dau punctele de experienta
                    //celuilalt, respectiv nivelul, dupa caz
                    this.deadPlayer(player1, player2, level2);
                    this.deadPlayer(player2, player1, level1);

                    break;
                }
            }
        }
    }

    public final void angelsActions(final int i) {
        for (Angels angel : gameInput.getAngels()){
            if (angel.getRound() != i){
                continue;
            } else {
                //doar daca ingerul actioneaza in runda curenta
                output = "Angel " + angel.getType() + " was spawned at "
                        + angel.getLine() + " " + angel.getColumn();
                greatWizard.setValue(output);
                for (HeroOnMap player : playersMap) {
                    //verific pentru fiecare jucator daca se afla in aceeasi
                    //pozitie cu ingerul
                    if (angel.getLine() != player.getLine() ||
                            angel.getColumn() != player.getColumn()) {
                        continue;
                    }
                    //daca este mort si ingerul nu il poate invia sar peste
                    if ((player.getHero().getHitPoints() <= 0 &&
                            !angel.getType().equals("Spawner"))
                            || (player.getHero().getHitPoints() > 0 &&
                            angel.getType().equals("Spawner"))) {
                        continue;
                    }
                    int level = player.getHero().getLevel();
                    player.getHero().acceptAngel(angel);
                    //mesajul daca jucatorul este lovit sau ajutat de inger
                    output = angel.getType() + " " + angel.getPredicate() + " "
                            + association.wordByLetter(player.getHero().getLetter())
                            + " " + player.getHero().getId();
                    greatWizard.setValue(output);
                    //mesajul de adus la viata/omorat/avansat in nivel
                    if (angel.getType().equals("Spawner")){
                        this.message(player, "  was brought to life by an angel");
                    }
                    this.nextLevel(level, player);
                    if (player.getHero().getHitPoints() <= 0) {
                        this.message(player, " was killed by an angel");
                    }
                }
            }
        }
        greatWizard.setValue("");
    }

    public final void finalResult(){
        //pentru fiecare jucator creez out-ul
        output = "~~ Results ~~" + "\n";
        for (HeroOnMap player : playersMap) {
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
    }
}
