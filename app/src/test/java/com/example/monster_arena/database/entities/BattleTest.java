package com.example.monster_arena.database.entities;

import static org.junit.Assert.assertNotEquals;

import junit.framework.TestCase;

public class BattleTest extends TestCase {

    private Battle battle;
    private Battle battle1;

    @Override
    protected void setUp() throws Exception {
        battle = new Battle("monster", "enemy", 0,"testUser", "Fire");
        battle1 = new Battle("monster1", "enemy1", 1, "testUser1", "Water");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetMonsterName() {
        assertNotNull(battle.getMonster_name());
        assertEquals("monster", battle.getMonster_name());
        battle.setMonster_name("dragon");
        assertNotEquals("monster", battle.getMonster_name());
        assertEquals("dragon", battle.getMonster_name());
    }

    public void testGetOpponentName() {
        assertNotNull(battle.getOpponent_name());
        assertEquals("enemy", battle.getOpponent_name());
        battle.setOpponent_name("adversary");
        assertNotEquals("enemy", battle.getOpponent_name());
        assertEquals("adversary", battle.getOpponent_name());
    }

    public void testGetUserName() {
        assertNotNull(battle.getUser_name());
        assertEquals("testUser", battle.getUser_name());
        battle.setUser_name("admin");
        assertNotEquals("testUser", battle.getUser_name());
        assertEquals("admin", battle.getUser_name());
    }

    public void testGetArenaName() {
        assertNotNull(battle.getArena_name());
        assertEquals("Fire", battle.getArena_name());
        battle.setArena_name("Water");
        assertNotEquals("Fire", battle.getArena_name());
        assertEquals("Water", battle.getArena_name());
    }

    public void testEquals() {
        assertNotEquals(battle, battle1);
        assertEquals(battle, battle);
        assertEquals(battle1, battle1);
    }
}
