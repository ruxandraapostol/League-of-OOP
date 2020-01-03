package main;

import angels.Angels;

import java.util.ArrayList;

public class GameInput {
    //dimensiunile terenului
    private final int n;
    private final int m;
    private final ArrayList<String> map;

    //jucatorii
    private final int p;
    private final String[][] players;

    //runde
    private final int r;
    private final ArrayList<String> move;

    //ingeri
    private final ArrayList<Angels> angels;

    public GameInput() {
        n = 0;
        m = 0;
        p = 0;
        r = 0;
        players = null;
        map = null;
        move = null;
        angels = null;
    }

    public GameInput(final int n, final int m, final ArrayList<String> map, final int p,
                     final String[][] players, final int r, final ArrayList<String> move,
                     final ArrayList<Angels> angels) {
        this.n = n;
        this.m = m;
        this.map = map;
        this.p = p;
        this.players = players;
        this.r = r;
        this.move = move;
        this.angels = angels;
    }

    public final int getP() {
        return p;
    }

    public final int getR() {
        return r;
    }

    public final String[][] getPlayers() {
        return players;
    }

    public final ArrayList<String> getMap() {
        return map;
    }

    public final ArrayList<String>  getMove() {
        return move;
    }

    public final ArrayList<Angels> getAngels() {
        return angels;
    }
}
