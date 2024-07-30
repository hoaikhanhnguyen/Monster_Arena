package com.example.monster_arena;

import java.util.Objects;

public class WaterArena implements Arena {
    @Override
    public void applyEffects(Monster monster) {
        if (Objects.requireNonNull(monster.getType()) == MonsterType.WATER) {
            monster.increaseAttackPower(15); // Boost water monsters
        }
    }
}
