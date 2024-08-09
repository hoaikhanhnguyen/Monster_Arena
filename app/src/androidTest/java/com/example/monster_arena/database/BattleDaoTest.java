package com.example.monster_arena.database;


import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.example.monster_arena.database.entities.Battle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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

        Battle retrievedBattle = dao.getBattles()
    }

    public void deleteBattleTest() {

    }

    @Test
    public void updateBattleTest() {

    }
}
