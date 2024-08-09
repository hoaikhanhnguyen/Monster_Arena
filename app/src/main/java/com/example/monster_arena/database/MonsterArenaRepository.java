package com.example.monster_arena.database;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;

import com.example.monster_arena.database.entities.Arena;
import com.example.monster_arena.database.entities.Battle;
import com.example.monster_arena.database.entities.MonsterArena;
import com.example.monster_arena.database.entities.Monsters;
import com.example.monster_arena.database.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MonsterArenaRepository {

    private final MonsterArenaDAO monsterArenaDAO;
    private final UserDAO userDAO;
    private final BattleDAO battleDAO;
    private final ArenaDAO arenaDAO;
    private final MonstersDAO monstersDAO;

    private ArrayList<MonsterArena> allLogs;

    private static MonsterArenaRepository repository;

    public MonsterArenaRepository(Application application) {
        MonsterArenaDatabase db = MonsterArenaDatabase.getDatabase(application);
        this.monsterArenaDAO = db.monsterArenaDAO();
        this.userDAO = db.userDAO();
        this.battleDAO = db.battleDAO();
        this.arenaDAO =db.arenaDAO();
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
            Log.i("Monster Arena", "Problem when getting all MonsterArenalogs in the repository");
        }
        return null;
    }

    public void insertMonsterArena(MonsterArena monsterArena) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            monsterArenaDAO.insert(monsterArena);
        });
    }

    public void insertUser(User... user) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            userDAO.insert(user);
        });
    }

    public void deleteUserById(int id) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            userDAO.deleteUserById(id);
        });
    }

    public void delete(User user) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            userDAO.delete(user);
        });
    }

    public void insertBattle(Battle... battle) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            battleDAO.insert(battle);
        });
    }

    public void insertArena(Arena... arena) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            arenaDAO.insert(arena);
        });
    }

    public void insertMonsters(Monsters monsters) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            monstersDAO.insert(monsters);
        });
    }

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    public User getUserByName(String username) {
        return userDAO.getUserByName(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateAdmin(String username) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            userDAO.updateAdmin(username);
        });
    }
    public List<Monsters> getAllMonsters() {return monstersDAO.getMonsters();}

    public LiveData<Monsters> getMonsterByMonsterName(String monsterName) {
        return monstersDAO.getMonsterByMonsterName(monsterName);
    }

    public Monsters getMonsterByName(String monsterName) {
        return monstersDAO.getMonsterByName(monsterName);
    }

    public void delete(Monsters monster) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            monstersDAO.delete(monster);
        });
    }

    public void insertNewArena(Arena arena) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            arenaDAO.insert(arena);
        });
    }
    public void deleteArenaById(int id) {
        MonsterArenaDatabase.databaseWriteExecutor.execute(() -> {
            arenaDAO.deleteArenaById(id);
        });
    }

    public LiveData<Battle> getRecentBattle() {
        return battleDAO.getRecentBattle();
    }

    public LiveData<List<Battle>> getAllBattles(int userId) {
        return battleDAO.getAllBattles(userId);
    }

    public LiveData<List<Arena>> getAllArenas(int userId) {
        return arenaDAO.getAllArenas();
    }


    public LiveData<List<Arena>> getAllArenas() {
        return arenaDAO.getAllArenas();
    }

    public Monsters getMonsterById(int id) {
        return monstersDAO.getMonsterNameById(id);
    }
    public Monsters getMonsterNameById(int id) {
        return monstersDAO.getMonsterNameById(id);
    }
}