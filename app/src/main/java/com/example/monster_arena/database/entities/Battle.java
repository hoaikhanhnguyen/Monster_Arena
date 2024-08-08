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
    private String user_name;
    private String arena_name;

    public Battle(String monster_name, String opponent_name, String user_name, String arena_name) {
        this.monster_name = monster_name;
        this.opponent_name = opponent_name;
        this.user_name = user_name;
        this.arena_name = arena_name;
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
        return id == battle.id && Objects.equals(monster_name, battle.monster_name) && Objects.equals(opponent_name, battle.opponent_name) && Objects.equals(user_name, battle.user_name) && Objects.equals(arena_name, battle.arena_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monster_name, opponent_name, user_name, arena_name);
    }

    @NonNull
    @Override
    public String toString() {
        setId(0); //TODO:Figure out how to take highest value in database and increment by 1
        return "Battle ID: " + id
                + "\nUsername: " + user_name
                + "\nMonster: " + monster_name
                + "\nOpponent: " + opponent_name
                + "\nArena: " + arena_name + "\n" +
                "--------------------------------";
    }
}


    /*
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

    @Override
    public String toString() {
        setId(0); //TODO:Figure out how to take highest value in database and increment by 1
        return "Battle ID: " + id
                + "\nUser ID: " + user_id
                + "\nMonster ID: " + monster_id
                + "\nOpponent ID: " + opponent_id
                + "\nArena ID: " + arena_id + "\n" +
                "--------------------------------";
    }
}
     */
