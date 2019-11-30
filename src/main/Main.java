package main;

import players.*;

public class Main {
    private Main() {
        throw new AssertionError("Error");
    }

    public static void main(final String[] args) {
        Input input = new Input(args[0], args[1]);
        GameInput gameInput = input.load();

        Heroes h1 = HeroesFactory.getInstance().getHeroesByLetter("W");
        Heroes h2 = HeroesFactory.getInstance().getHeroesByLetter("R");
        h2.accept(h1);
        h1.accept(h2);
        System.out.println("W: " + h1.getHitPoints());
        System.out.println("R: " + h2.getHitPoints());
    }
}
