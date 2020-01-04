package strategy;

import players.Heroes;

public interface Strategy {
    void operation(Heroes hero, float hp, float damage);
}
