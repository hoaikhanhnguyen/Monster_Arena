package com.example.monster_arena.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.monster_arena.database.entities.Monsters;

import java.util.List;

@Dao
public interface MonstersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Monsters monsters);

    @Delete
    void delete(Monsters monster);

    @Query("Select * from " + MonsterArenaDatabase.MONSTERS_TABLE)
    List<Monsters> getMonsters();

    @Query("DELETE FROM " + MonsterArenaDatabase.MONSTERS_TABLE + " WHERE id = :id ")
    void deleteMonsterById(Integer id);

    @Query("DELETE from " + MonsterArenaDatabase.MONSTERS_TABLE )
    void deleteAll();

    @Query("SELECT * FROM " + MonsterArenaDatabase.MONSTERS_TABLE + " WHERE name == :name ")
    LiveData<Monsters> getMonsterByMonsterName(String name);

    @Query("SELECT * FROM " + MonsterArenaDatabase.MONSTERS_TABLE + " WHERE name == :name")
    Monsters getMonsterByName(String name);





}
