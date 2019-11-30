package main;

import java.util.ArrayList;

public class GameInput {
    //dimensiunile terenului
    private final int N;
    private final int M;
    private final ArrayList<String> map;

    //jucatorii
    private final int P;
    private final String players[][];

    //runde
    private final int R;
    private final ArrayList<String> move;

    public GameInput() {
        N = 0;
        M = 0;
        P = 0;
        R = 0;
        players = null;
        map = null;
        move = null;
    }

    public GameInput(final int n, final int m, final ArrayList<String> map, final int p,
               final String players[][], final int r, final ArrayList<String> move) {
        N = n;
        M = m;
        this.map = map;
        P = p;
        this.players = players;
        R = r;
        this.move = move;
    }

    public int getM() {
        return M;
    }

    public int getN() {
        return N;
    }

    public int getP() {
        return P;
    }

    public int getR() {
        return R;
    }

    public String[][] getPlayers() {
        return players;
    }

    public ArrayList<String> getMap() {
        return map;
    }

    public ArrayList<String>  getMove() {
        return move;
    }
}
