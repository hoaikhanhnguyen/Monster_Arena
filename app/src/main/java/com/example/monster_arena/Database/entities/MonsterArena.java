package com.example.monster_arena.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "monsterArena")
public class MonsterArena {
    @PrimaryKey(autoGenerate = true)
    private int id;

    //generate constructor, setters and getters, and equals and hashcode
}
