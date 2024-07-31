package com.example.monster_arena.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.monster_arena.database.entities.MonsterArena;
import com.example.monster_arena.database.entities.Monsters;
import com.example.monster_arena.database.typeConverters.LocalDateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {MonsterArena.class}, version = 1, exportSchema = false)
public abstract class MonsterArenaDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "MonsterArena_database";

    // Define all Table names
    public static final String  MONSTER_ARENA_TABLE = "monsterArenaTable";
    public static final String MONSTERS_TABLE = "monstersTable";

    private static volatile MonsterArenaDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MonsterArenaDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized(MonsterArenaDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    MonsterArenaDatabase.class,
                                    DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i("Monster Arena", "DATABASE CREATED!");
            //TODO: add databaseWriteExecutor.execute(() -> {...}
        }
    };

    public abstract MonsterArenaDAO monsterArenaDAO();

    public abstract MonstersDAO monstersDAO();
}
