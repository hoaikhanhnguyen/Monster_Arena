package com.example.monster_arena;

import java.util.Objects;

public class FireArena implements Arena {
    /**
     * This method checks for fire monsters and boost fire monster by 15 attack
     * @param monster
     */

        @Override
        public void applyEffects(Monster monster) {
            if (MonsterType.FIRE.equals(monster.getType())) {
                monster.increaseAttackPower(15); //
            }
        }
}
