package com.example.monster_arena.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.monster_arena.database.entities.Arena;
import com.example.monster_arena.database.entities.Battle;
import com.example.monster_arena.database.entities.MonsterArena;
import com.example.monster_arena.database.entities.Monsters;
import com.example.monster_arena.database.entities.User;
import com.example.monster_arena.database.typeConverters.LocalDateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {MonsterArena.class, User.class, Battle.class, Arena.class, Monsters.class}, version = 8, exportSchema = false)
public abstract class MonsterArenaDatabase extends RoomDatabase {


    private static final String DATABASE_NAME = "MonsterArenaDatabase";

    // Define all Table names
    public static final String USER_TABLE = "userTable";
    public static final String  MONSTER_ARENA_TABLE = "monsterArenaTable";
    public static final String BATTLE_TABLE = "battleTable";
    public static final String ARENA_TABLE = "arenaTable";
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
                            .allowMainThreadQueries()
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
            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();
                User admin = new User("admin", "admin");
                admin.setAdmin(true);
                dao.insert(admin);
                User testUser1 = new User("testUser", "testUser");
                dao.insert(testUser1);

            });
            databaseWriteExecutor.execute(() -> {
                MonstersDAO dao = INSTANCE.monstersDAO();
                dao.deleteAll();
                Monsters monster = new Monsters("Squirtail", "Water type monster", 1, 1, 10.0, "Water", 1,1,1,1.0);
                dao.insert(monster);
                monster = new Monsters("Charlizard", "Fire type monster", 1, 1, 10.0, "Water", 1,1,1,1.0);
                dao.insert(monster);
                monster = new Monsters("Bulbguy", "Grass type monster", 1, 1, 10.0, "Water", 1,1,1,1.0);
                dao.insert(monster);
            });
        }
    };

    public abstract MonsterArenaDAO monsterArenaDAO();

    public abstract UserDAO userDAO();

    public abstract BattleDAO battleDAO();

    public abstract ArenaDAO arenaDAO();

    public abstract MonstersDAO monstersDAO();
}