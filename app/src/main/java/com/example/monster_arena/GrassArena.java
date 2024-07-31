package com.example.monster_arena;

import java.util.Objects;

public class GrassArena implements Arena {

    /**
     * This method checks for grass monsters and boost grass monster by 15 attack
     * @param monster
     */
    @Override
    public void applyEffects(Monster monster) {
        if (MonsterType.GRASS.equals(monster.getType())) {
            monster.increaseAttackPower(15); // Boost water monsters
        }
    }
}
