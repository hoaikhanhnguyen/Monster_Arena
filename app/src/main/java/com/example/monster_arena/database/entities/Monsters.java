package com.example.monster_arena.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.monster_arena.database.MonsterArenaDatabase;

import java.time.LocalDate;
import java.util.Objects;

@Entity(tableName = MonsterArenaDatabase.MONSTERS_TABLE)
public class Monsters {
    @PrimaryKey(autoGenerate = true)
    private String name;
    private String description;
    private Integer user_id;
    private Integer level;
    private double next_level;
    private String type;
    private Integer attack;
    private Integer defense;
    private Integer agility;
    private double experience;
    private LocalDate created_at;

    public Monsters(String name, String description, Integer user_id, Integer level, double next_level, String type, Integer attack, Integer defense, Integer agility, double experience, LocalDate created_at) {
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        this.level = level;
        this.next_level = next_level;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.agility = agility;
        this.experience = experience;
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monsters monsters = (Monsters) o;
        return Double.compare(next_level, monsters.next_level) == 0 && Double.compare(experience, monsters.experience) == 0 && Objects.equals(name, monsters.name) && Objects.equals(description, monsters.description) && Objects.equals(user_id, monsters.user_id) && Objects.equals(level, monsters.level) && Objects.equals(type, monsters.type) && Objects.equals(attack, monsters.attack) && Objects.equals(defense, monsters.defense) && Objects.equals(agility, monsters.agility) && Objects.equals(created_at, monsters.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, user_id, level, next_level, type, attack, defense, agility, experience, created_at);
    }




}
