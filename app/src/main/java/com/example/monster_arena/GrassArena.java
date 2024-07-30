package com.example.monster_arena;

import java.util.Objects;

public class GrassArena implements Arena {
    @Override
    public void applyEffects(Monster monster) {
        if (Objects.requireNonNull(monster.getType()) == MonsterType.GRASS) {
            monster.increaseAttackPower(15); // Boost grass monsters
        }
    }
}
