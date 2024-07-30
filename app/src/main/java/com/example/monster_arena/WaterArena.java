package com.example.monster_arena;

public class WaterArena implements Arena {
    @Override
    public void applyEffects(Monster monster) {
        switch (monster.getType()) {
            case WATER:
                monster.increaseAttackPower(15); // Boost water monsters
                break;
            case FIRE:
                monster.decreaseAttackPower(10); // Weaken fire monsters
                break;
            case GRASS:
                monster.decreaseAttackPower(10); // Weaken grass monsters
                break;
        }
    }
}
