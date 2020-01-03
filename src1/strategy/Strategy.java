package strategy;

import players.Heroes;

public interface Strategy {
    public void operation (Heroes hero, float hp, float damage);
}
