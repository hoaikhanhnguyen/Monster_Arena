package com.example.monster_arena;

public class Monster {
    private MonsterType type;
    private int attackPower;

    // Constructor
    public Monster(MonsterType type, int attackPower) {
        this.type = type;
        this.attackPower = attackPower;
    }

    public MonsterType getType() {
        return type;
    }

    public void increaseAttackPower(int amount) {
        attackPower += amount;
    }

    public void decreaseAttackPower(int amount) {
        attackPower -= amount;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Attack Power: " + attackPower;
    }
}
