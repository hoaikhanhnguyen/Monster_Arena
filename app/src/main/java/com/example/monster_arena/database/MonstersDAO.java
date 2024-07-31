package com.example.monster_arena.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.monster_arena.database.entities.Monsters;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MonstersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Monsters[] monsters);

    @Query("Select * from " + MonsterArenaDatabase.MONSTERS_TABLE)
    List<Monsters> getMonsters();
}
