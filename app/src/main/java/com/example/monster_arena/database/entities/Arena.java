package com.example.monster_arena.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import com.example.monster_arena.database.MonsterArenaDatabase;

import java.util.Objects;

@Entity(tableName = MonsterArenaDatabase.ARENA_TABLE)
public class Arena {

    @PrimaryKey(autoGenerate = true)

    private String fireArena;
    private String waterArena;
    private String grassArena;

    //generate constructor, setters and getters, and equals and hashcode


    public Arena(String fireArena, String waterArena, String grassArena) {
        this.fireArena = fireArena;
        this.waterArena = waterArena;
        this.grassArena = grassArena;
    }

    public String getFireArena() {
        return fireArena;
    }

    public void setFireArena(String fireArena) {
        this.fireArena = fireArena;
    }

    public String getWaterArena() {
        return waterArena;
    }

    public void setWaterArena(String waterArena) {
        this.waterArena = waterArena;
    }

    public String getGrassArena() {
        return grassArena;
    }

    public void setGrassArena(String grassArena) {
        this.grassArena = grassArena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arena arena = (Arena) o;
        return Objects.equals(fireArena, arena.fireArena) && Objects.equals(waterArena, arena.waterArena) && Objects.equals(grassArena, arena.grassArena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fireArena, waterArena, grassArena);
    }
}