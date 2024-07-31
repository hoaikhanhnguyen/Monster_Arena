package com.example.monster_arena;

import java.util.Objects;

public class WaterArena implements Arena {

    /**
     * This method checks for water monsters and boost water monster by 15 attack
     * @param monster
     */
    @Override
    public void applyEffects(Monster monster) {
        if (MonsterType.WATER.equals(monster.getType())) {
            monster.increaseAttackPower(15); // Boost water monsters
        }
    }
}
