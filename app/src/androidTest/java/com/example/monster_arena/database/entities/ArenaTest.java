package com.example.monster_arena.database.entities;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.monster_arena.database.entities.Arena;

public class ArenaTest {

    private Arena arena;

    @Before
    public void setUp() {
        arena = new Arena("Tired", "Fire");
    }

    @Test
    public void testGetId() {
        arena.setId(1);
        assertEquals(1, arena.getId());
    }

    @Test
    public void testSetId() {
        arena.setId(2);
        assertEquals(2, arena.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Tired", arena.getName());
    }

    @Test
    public void testSetName() {
        arena.setName("JoeBlow");
        assertEquals("JoeBlow", arena.getName());
    }

    @Test
    public void testGetType() {
        assertEquals("Fire", arena.getType());
    }

    @Test
    public void testSetType() {
        arena.setType("Water");
        assertEquals("Water", arena.getType());
    }


}
