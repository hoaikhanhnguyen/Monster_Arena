package com.example.monster_arena.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.example.monster_arena.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserDaoTest {

    private MonsterArenaDatabase database;
    private UserDAO dao;

    @Before
    public void setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                MonsterArenaDatabase.class
        ).allowMainThreadQueries().build();
        dao = database.userDAO();
    }

    @After
    public void teardown() {
        database.close();
    }

    @Test
    public void insertUserTest() {
        User testUser = new User("testUser", "password");
        dao.insert(testUser);

        User retrievedUser = dao.getUserByName("testUser");
        assertEquals("testUser", retrievedUser.getUserName());
    }

    @Test
    public void deleteUserTest() {
        User testUser = new User("testUser", "password");
        dao.insert(testUser);

        dao.delete(testUser);
        assertNotEquals(testUser, dao.getUserByName(testUser.getUserName()));
    }

    @Test
    public void updateUserAdminTest() {
        User testUser = new User("testUser", "password");
        dao.insert(testUser);

        User retrievedUser = dao.getUserByName("testUser");
        assertEquals("testUser", retrievedUser.getUserName());
        assertFalse(retrievedUser.isAdmin());


        dao.updateAdmin(testUser.getUserName());
        User retrievedUserAgain = dao.getUserByName("testUser");
        assertTrue(retrievedUserAgain.isAdmin());
    }
}
