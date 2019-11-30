package main;

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

    public GameInput() {
        n = 0;
        m = 0;
        p = 0;
        r = 0;
        players = null;
        map = null;
        move = null;
    }

    public GameInput(final int n, final int m, final ArrayList<String> map, final int p,
               final String[][] players, final int r, final ArrayList<String> move) {
        this.n = n;
        this.m = m;
        this.map = map;
        this.p = p;
        this.players = players;
        this.r = r;
        this.move = move;
    }

    public final int getM() {
        return m;
    }

    public final int getN() {
        return n;
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
}
