package com.example.monster_arena;

public class GrassArena implements Arena {
    @Override
    public void applyEffects(Monster monster) {
        switch (monster.getType()) {
            case GRASS:
                monster.increaseAttackPower(15); // Boost grass monsters
                break;
            case FIRE:
                monster.decreaseAttackPower(10); // Weaken fire monsters
                break;
            case WATER:
                monster.increaseAttackPower(5); // Boost grass monsters against water
                break;
        }
    }
}
