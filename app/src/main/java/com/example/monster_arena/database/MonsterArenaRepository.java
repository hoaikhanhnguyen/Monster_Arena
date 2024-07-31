package com.example.monster_arena.database;

import android.app.Application;
import android.util.Log;

import com.example.monster_arena.database.entities.MonsterArena;
import com.example.monster_arena.database.entities.Monsters;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MonsterArenaRepository {

    private MonsterArenaDAO monsterArenaDAO;
    private MonstersDAO monstersDAO;
    private ArrayList<MonsterArena> allLogs;

    private static MonsterArenaRepository repository;

    public MonsterArenaRepository(Application application) {
        MonsterArenaDatabase db = MonsterArenaDatabase.getDatabase(application);
        this.monsterArenaDAO = db.monsterArenaDAO();
        this.monstersDAO = db.monstersDAO();

        this.allLogs = (ArrayList<MonsterArena>) this.monsterArenaDAO.getAllRecords();

    }

    public static MonsterArenaRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<MonsterArenaRepository> future =  MonsterArenaDatabase.databaseWriteExecutor.submit(
                new Callable<MonsterArenaRepository>() {
                    @Override
                    public MonsterArenaRepository call() throws Exception {
                        return new MonsterArenaRepository(application);
                    }
                }
        );
        try {
            return future.get();
        }catch (InterruptedException | ExecutionException e) {
            Log.d("Monster Arena", "Problem getting MonsterArenaRepository, thread error.");
        }
        return null;
    }

    public ArrayList<MonsterArena> getAllLogs() {
        Future<ArrayList<MonsterArena>> future = MonsterArenaDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<MonsterArena>>() {
                    @Override
                    public ArrayList<MonsterArena> call() throws Exception{
                        return (ArrayList<MonsterArena>) monsterArenaDAO.getAllRecords();
                    }
                }
        );
        try {
            return future.get();
        }catch (InterruptedException | ExecutionException e) {
            Log.i("Monster Arena", "Problem when getting all Gymlogs in the repository");
        }
        return null;
    }

    public void insertGymLog(MonsterArena monsterArena) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            monsterArenaDAO.insert(monsterArena);
        });
    }

    public void insertMonsters (Monsters monsters){
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
          monstersDAO.insert(monsters);
        });
    }


}
