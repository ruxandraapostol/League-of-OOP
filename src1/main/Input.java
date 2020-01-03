package main;

import angels.Angels;
import angels.AngelsFactory;
import fileio.FileSystem;

import java.io.IOException;
import java.util.ArrayList;

public class Input {
    public static final int CONSTANT = 3;
    private final String inputFile;
    private final String outputFile;

    public Input(final String inputFile, final String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    //citirea din fisier
    public final GameInput load() {
        try {
            FileSystem fs = new FileSystem(inputFile, outputFile);
            int n = fs.nextInt();
            int m = fs.nextInt();
            ArrayList<String> map = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                map.add(fs.nextWord());
            }
            int p = fs.nextInt();
            String[][] players = new String[p][CONSTANT];
            for (int i = 0; i < p; i++) {
                for (int j = 0; j < CONSTANT; j++) {
                    players[i][j] = fs.nextWord();
                }
            }
            int r = fs.nextInt();
            ArrayList<String> move = new ArrayList<>();
            for (int j = 0; j < r; j++) {
                move.add(fs.nextWord());
            }

            ArrayList<Angels> angels = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                int nrAngels = fs.nextInt();
                for (int j = 0; j < nrAngels; j++) {
                    String angel = fs.nextWord();
                    String[] angelList = angel.split(",");
                    angels.add(AngelsFactory.getAngelInstance().getAngelsByType(
                    angelList[0], Integer.parseInt(angelList[1]),
                            Integer.parseInt(angelList[2]), i));
                }
            }
            fs.close();
            return new GameInput(n, m, map, p, players, r, move, angels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GameInput();
    }

    // scrierea in fisier
    public final void write(final String s) {
        try {
            FileSystem fs = new FileSystem(inputFile, outputFile);
            fs.writeWord(s);
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
