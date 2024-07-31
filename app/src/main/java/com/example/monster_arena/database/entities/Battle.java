package com.example.monster_arena.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.monster_arena.database.MonsterArenaDatabase;

import java.util.Objects;

@Entity(tableName = MonsterArenaDatabase.USER_TABLE)
public class Battle {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int monster_id;
    private int opponent_id;
    private int user_id;
    private int arena_id;

    public Battle(int monster_id, int opponent_id, int user_id, int arena_id) {
        this.monster_id = monster_id;
        this.opponent_id = opponent_id;
        this.user_id = user_id;
        this.arena_id = arena_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonster_id() {
        return monster_id;
    }

    public void setMonster_id(int monster_id) {
        this.monster_id = monster_id;
    }

    public int getOpponent_id() {
        return opponent_id;
    }

    public void setOpponent_id(int opponent_id) {
        this.opponent_id = opponent_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getArena_id() {
        return arena_id;
    }

    public void setArena_id(int arena_id) {
        this.arena_id = arena_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return id == battle.id && monster_id == battle.monster_id && opponent_id == battle.opponent_id && user_id == battle.user_id && arena_id == battle.arena_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monster_id, opponent_id, user_id, arena_id);
    }
}
