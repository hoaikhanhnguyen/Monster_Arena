package com.example.monster_arena.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.monster_arena.database.entities.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + MonsterArenaDatabase.USER_TABLE + " ORDER BY userName")
    List<User> getAllUsers();

    @Query("DELETE from " + MonsterArenaDatabase.USER_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + MonsterArenaDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUserName(String username);

    @Query("SELECT * FROM " + MonsterArenaDatabase.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);

}
