package com.example.monster_arena.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import com.example.monster_arena.database.entities.Monsters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MonsterDaoTest {

    private MonsterArenaDatabase database;
    private MonstersDAO dao;

    @Before
    public void setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                MonsterArenaDatabase.class
        ).allowMainThreadQueries().build();
        dao = database.monstersDAO();
    }

    @After
    public void teardown() {
        database.close();
    }

    @Test
    public void insertMonsterTest() {
        Monsters monster = new Monsters("WaterNomad", "Water type monster", 1, 1, 10.0, "Water", 1, 1, 1, 1, 1.0);
        dao.insert(monster);

        Monsters retrieveMonster = dao.getMonsterByName("WaterNomad");
        assertEquals("WaterNomad", retrieveMonster.getName());
    }

    @Test
    public void deleteMonsterTest() {
        Monsters monster = new Monsters("GrassBender", "Grass type monster", 1, 1, 10.0, "Grass", 1, 1, 1, 1, 1.0);
        dao.insert(monster);
        dao.insert(monster);

        dao.delete(monster);
        assertNotEquals(monster, dao.getMonsterByName(monster.getName()));
    }

    @Test
    public void updateMonsterStats() {
        Monsters monster = new Monsters("FireBender", "Fire type monster", 1, 1, 10.0, "Fire", 1, 1, 1, 1, 1.0);
        dao.insert(monster);
        dao.insert(monster);

        Monsters monsters = dao.getMonsterByName("FireBender");
        assertEquals("FireBender", monsters.getName());
        monster.setAgility(5);
        long stat = 5;
        long monsterStat = monster.getAgility();
        assertEquals(stat, monsterStat);
        assertNotEquals(6, monsterStat);

        monster.setAttack(20);
        long statAttack = 20;
        long monsterAttack = monster.getAttack();
        assertEquals(statAttack, monsterAttack);
        assertNotEquals(6, monsterAttack);

        monster.setDefense(15);
        long statDefense = 15;
        long monsterDef = monster.getDefense();
        assertEquals(statDefense, monsterDef);
        assertNotEquals(16, monsterDef);



    }
}
