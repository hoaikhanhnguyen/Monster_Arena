package com.example.monster_arena.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.monster_arena.database.entities.Arena;


import java.util.List;

@Dao
public interface ArenaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Arena... arena);

    @Query("SELECT * FROM " + MonsterArenaDatabase.ARENA_TABLE)
    List<Arena> getAllArenas();

    @Query("DELETE FROM " + MonsterArenaDatabase.ARENA_TABLE + " WHERE id = :arenaId")
    void deleteArenaById(int arenaId);

}
