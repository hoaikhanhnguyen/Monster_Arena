package com.example.monster_arena.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.monster_arena.database.MonsterArenaDatabase;

import java.util.Objects;

@Entity(tableName = MonsterArenaDatabase.BATTLE_TABLE)
public class Battle {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String monster_name;
    private String opponent_name;
    private int user_id;
    private String user_name;
    private String arena_name;

    public Battle(String monster_name, String opponent_name, int user_id, String user_name, String arena_name) {
        this.monster_name = monster_name;
        this.opponent_name = opponent_name;
        this.user_id = user_id;
        this.user_name = user_name;
        this.arena_name = arena_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonster_name() {
        return monster_name;
    }

    public void setMonster_name(String monster_name) {
        this.monster_name = monster_name;
    }

    public String getOpponent_name() {
        return opponent_name;
    }

    public void setOpponent_name(String opponent_name) {
        this.opponent_name = opponent_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getArena_name() {
        return arena_name;
    }

    public void setArena_name(String arena_name) {
        this.arena_name = arena_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return id == battle.id && user_id == battle.user_id && Objects.equals(monster_name, battle.monster_name) && Objects.equals(opponent_name, battle.opponent_name) && Objects.equals(user_name, battle.user_name) && Objects.equals(arena_name, battle.arena_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monster_name, opponent_name, user_id, user_name, arena_name);
    }

    @NonNull
    @Override
    public String toString() {
        return "Battle ID: " + id
                + "\nUsername: " + user_name
                + "\nMonster: " + monster_name
                + "\nOpponent: " + opponent_name
                + "\nArena: " + arena_name + "\n" +
                "--------------------------------\n";
    }
}
