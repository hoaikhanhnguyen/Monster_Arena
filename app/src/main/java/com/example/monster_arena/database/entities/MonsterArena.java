package com.example.monster_arena.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.monster_arena.database.MonsterArenaDatabase;

@Entity(tableName = MonsterArenaDatabase.MONSTER_ARENA_TABLE)
public class MonsterArena {
    @PrimaryKey(autoGenerate = true)
    private int id;

    //generate constructor, setters and getters, and equals and hashcode

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}