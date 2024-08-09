package com.example.monster_arena.database;


import static junit.framework.TestCase.assertEquals;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.example.monster_arena.database.entities.Battle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class BattleDaoTest {

    private MonsterArenaDatabase database;
    private BattleDAO dao;

    @Before
    public void setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                MonsterArenaDatabase.class
        ).allowMainThreadQueries().build();
        dao = database.battleDAO();
    }

    @After
    public void teardown() {
        database.close();
    }

    @Test
    public void insertBattleTest() {
        Battle battle = new Battle("monster", "enemy", 0, "user", "arena");
        dao.insert(battle);

        List<Battle> allBattles = dao.getBattles();
        Battle retrievedBattle = allBattles.get(0);
        assertEquals("monster", retrievedBattle.getMonster_name());
        assertEquals("enemy", retrievedBattle.getOpponent_name());
        assertEquals(0, retrievedBattle.getUser_id());
        assertEquals("user", retrievedBattle.getUser_name());
        assertEquals("arena", retrievedBattle.getArena_name());
    }

    @Test
    public void deleteAllBattleTest() {
        Battle battle = new Battle("monster", "enemy", 0, "user", "arena");
        Battle battle1 = new Battle("monster1", "enemy1", 1, "user1", "arena1");
        dao.insert(battle, battle1);

        List<Battle> allBattles = dao.getBattles();
        assertEquals(2, allBattles.size());

        dao.deleteAll();
        allBattles = dao.getBattles();
        assertEquals(0,allBattles.size());
    }

    @Test
    public void getRecentBattleTest() {
        Battle battle = new Battle("monster", "enemy", 0, "user", "arena");
        Battle battle1 = new Battle("monster1", "enemy1", 1, "user1", "arena1");
        dao.insert(battle, battle1);

        LiveData<Battle> liveData = dao.getRecentBattle();
    }
}
