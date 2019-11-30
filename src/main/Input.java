package main;

import fileio.FileSystem;
import java.util.ArrayList;

public class Input {
    private final String inputFile;
    private final String outputFile;

    public Input(final String inputFile, final String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public GameInput load() {
        try {
            FileSystem fs = new FileSystem(inputFile, outputFile);
            int N = fs.nextInt();
            int M = fs.nextInt();
            ArrayList<String> map = new ArrayList<>();
            for (int i = 0; i < N; i++){
                map.add(fs.nextWord());
            }
            int P = fs.nextInt();
            String players[][] = new String[P][3];
            for (int i = 0; i < P; i++){
                for (int j = 0; j < 3; j++){
                    players[i][j] = fs.nextWord();
                }
            }
            int R = fs.nextInt();
            ArrayList<String> move = new ArrayList<>();
            for (int j = 0; j < R; j ++) {
                move.add(fs.nextWord());
            }
            fs.close();
            return new GameInput(N, M, map, P, players, R, move);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GameInput();
    }
}
