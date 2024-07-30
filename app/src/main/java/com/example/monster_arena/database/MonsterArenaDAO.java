package com.example.monster_arena.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.monster_arena.database.entities.MonsterArena;

import java.util.List;

@Dao
public interface MonsterArenaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MonsterArena monsterArena);

    @Query("Select * from " + MonsterArenaDatabase.MONSTER_ARENA_TABLE)
    List<MonsterArena> getAllRecords();
}
