package com.example.monster_arena.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.monster_arena.database.entities.Monsters;

import java.util.ArrayList;

@Dao
public interface MonstersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Monsters gymLog);

    @Query("Select * from " + MonstersDatabase.MONSTERS_TABLE)
    ArrayList<Monsters> getAllRecords();
}
