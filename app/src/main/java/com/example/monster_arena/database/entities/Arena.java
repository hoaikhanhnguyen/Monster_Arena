package com.example.monster_arena.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.monster_arena.database.MonsterArenaDatabase;

import java.util.Objects;

@Entity(tableName = MonsterArenaDatabase.ARENA_TABLE)
public class Arena {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String type;



    public Arena(String name, String type) {
        this.name = name;
        this.type = type;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arena arena = (Arena) o;
        return id == arena.id && Objects.equals(name, arena.name) && Objects.equals(type, arena.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}

