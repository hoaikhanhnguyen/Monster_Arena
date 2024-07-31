package com.example.monster_arena.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.monster_arena.database.MonsterArenaDatabase;

import java.util.Objects;

@Entity(tableName = MonsterArenaDatabase.ARENA_TABLE)

public class Arena {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String fireArena;
    private String grassArena;
    private String waterArena;

    public Arena(String fireArena, String grassArena, String waterArena) {
        this.fireArena = fireArena;
        this.grassArena = grassArena;
        this.waterArena = waterArena;
    }

    public String getFireArena() {
        return fireArena;
    }

    public void setFireArena(String fireArena) {
        this.fireArena = fireArena;
    }

    public String getGrassArena() {
        return grassArena;
    }

    public void setGrassArena(String grassArena) {
        this.grassArena = grassArena;
    }

    public String getWaterArena() {
        return waterArena;
    }

    public void setWaterArena(String waterArena) {
        this.waterArena = waterArena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arena arena = (Arena) o;
        return Objects.equals(fireArena, arena.fireArena) && Objects.equals(grassArena, arena.grassArena) && Objects.equals(waterArena, arena.waterArena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fireArena, grassArena, waterArena);
    }


}