package com.example.monster_arena;

public class FireArena implements Arena {
        @Override
        public void applyEffects(Monster monster) {
                switch (monster.getType()) {
                        case FIRE:
                                monster.increaseAttackPower(15); // Boost fire monsters
                                break;
                        case GRASS:
                                monster.decreaseAttackPower(10); // Weaken grass monsters
                                break;
                        case WATER:
                                monster.decreaseAttackPower(5); // Weaken water monsters slightly
                                break;
                }
        }
}
