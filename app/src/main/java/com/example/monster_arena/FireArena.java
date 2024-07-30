package com.example.monster_arena;

import java.util.Objects;

public class FireArena implements Arena {
        @Override
        public void applyEffects(Monster monster) {
            if (Objects.requireNonNull(monster.getType()) == MonsterType.FIRE) {
                monster.increaseAttackPower(15); // Boost fire monsters
            }
        }
}
