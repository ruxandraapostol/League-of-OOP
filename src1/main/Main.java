package main;

public final class Main {
    private Main() {
        throw new AssertionError("Error");
    }

    public static void main(final String[] args) {
        Input input = new Input(args[0], args[1]);
        Play play = new Play(input);
        GameInput gameInput = input.load();

        //pentru fiecare runda
        for (int i = 0; i < gameInput.getR(); i++) {
            play.beforeTheRound(i);
            play.fight();
            play.angelsActions(i);
        }

        play.finalResult();
    }
}

