package com.example.monster_arena.database;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.monster_arena.database.entities.Battle;

import java.util.List;

public interface BattleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Battle... battle);

    @Query("SELECT * FROM" + MonsterArenaDatabase.BATTLE_TABLE + " ORDER BY id")
    List<Battle> getBattles();
}
