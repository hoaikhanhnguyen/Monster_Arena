package com.example.monster_arena.database.entities;

import static org.junit.Assert.assertNotEquals;
import junit.framework.TestCase;

public class UserTest extends TestCase {

    private User user;
    private User user2;

    @Override
    public void setUp() {
        user = new User("testUser", "password");
        user2 = new User("admin", "admin");

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetUserName() {
        assertNotEquals(null, user.getUserName());
        assertEquals("testUser", user.getUserName());
        user.setUserName("admin");
        assertNotEquals("testUser", user.getUserName());
        assertEquals("admin", user.getUserName());
    }

    public void testGetPassWord() {
        assertNotEquals(null, user.getPassword());
        assertEquals("password", user.getPassword());
        user.setPassword("newPassword");
        assertNotEquals("password", user.getPassword());
        assertEquals("newPassword", user.getPassword());
    }

    public void testGetAdmin() {
        assertNotEquals(null, user.isAdmin());
        assertFalse(user.isAdmin());
        user.setAdmin(true);
        assertNotEquals(null, user.isAdmin());
        assertNotEquals(false, user.isAdmin());
        assertTrue(user.isAdmin());
    }

    public void testEquals() {
        assertNotEquals(user, user2);
        assertEquals(user, user);
        assertEquals(user2, user2);
    }
}