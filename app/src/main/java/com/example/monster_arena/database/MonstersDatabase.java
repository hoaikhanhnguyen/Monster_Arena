package com.example.monster_arena.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.strictmode.SetRetainInstanceUsageViolation;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.monster_arena.database.entities.Monsters;
import com.example.monster_arena.MonstersActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Monsters.class}, version = 1, exportSchema = false)
public abstract class MonstersDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Monsters_database";

    public static final String MONSTERS_TABLE = "monstersTable";

    private static volatile MonstersDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MonstersDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (MonstersDatabase.class){
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    MonstersDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MonstersActivity.TAG,"DATABASE CREATED!");
            //TODO add database WriteExecutor.execute(() -> {...}
        }
    };

    public abstract MonstersDAO monstersDAO();
}
