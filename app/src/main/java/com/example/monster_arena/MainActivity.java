package com.example.monster_arena;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.Battle;
import com.example.monster_arena.database.entities.Monsters;
import com.example.monster_arena.database.entities.User;
import com.example.monster_arena.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.monster_arena.MAIN_ACTIVITY_USER_ID";
    static final String SHARED_PREFERENCE_USERID_KEY =" com.example.monster_arena.SHARED_PREFERENCE_USERID_KEY";
    static final String SHARED_PREFERENCE_USERID_VALUE =" com.example.monster_arena.SHARED_PREFERENCE_USERID_VALUE";
    static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.example.monster_arena.SHARED_INSTANCE_STATE_USERID_KEY";

    private static final int LOGGED_OUT = -1;

    private int loggedInUserId = -1;
    private User user;

    private ActivityMainBinding binding;
    private MonsterArenaRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());
        loginUser(savedInstanceState);

        if(loggedInUserId == -1) {
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }

        updateSharedPreference();

        binding.manageUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ManageUserActivity.manageUserActivityIntentFactory(getApplicationContext()));
            }
        });

        binding.battleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = loggedInUserId;
                String userName = user.getUserName();
                Monsters userMonster = randomMonster(); //TODO: How do i get the user's monster? Using a randomMonster for now.
                Monsters enemyMonster = randomMonster();
                String battleResultValue = battleLogic(userMonster, enemyMonster);
                String arenaName = "arena1"; //TODO: Needs to be updated.

                Battle battle = new Battle(userMonster.getName(), enemyMonster.getName(), userId, userName, arenaName);
                repository.insertBattle(battle);

                Intent intent = BattleResults.battleResultsIntentFactory(getApplicationContext(), userId);
                intent.putExtra("battleResult", battleResultValue);
                startActivity(intent);
            }
        });

        binding.arenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = loggedInUserId;
                Intent intent = ArenaTypes.arenaTypesIntentFactory(getApplicationContext(), userId);
                startActivity(intent);
            }
        });
        binding.monsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = loggedInUserId;
                Intent intent = MonstersActivity.manageMonsterActivityIntentFactory(getApplicationContext(), userId);
                startActivity(intent);
            }
        });
    }

    private void loginUser(Bundle savedInstanceState) {
        // Check shared preferences for logged in user
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_FILE_KEY),
                Context.MODE_PRIVATE);

        loggedInUserId = sharedPreferences.getInt(getString(R.string.PREFERENCE_USERID_KEY), LOGGED_OUT);

        if (loggedInUserId == LOGGED_OUT  && savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_STATE_USERID_KEY)) {
            loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_STATE_USERID_KEY, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT) {
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT) {
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if(user != null) {
                invalidateOptionsMenu();
                checkAdmin(user);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INSTANCE_STATE_USERID_KEY, loggedInUserId);
        updateSharedPreference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.logoutMenuItem);
        item.setVisible(true);
        if(user == null) {
            return false;
        }
        item.setTitle(user.getUserName());
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                showLogoutDialog();
                return false;
            }
        });
        return true;
    }

    private void checkAdmin(User user) {
        if (user.isAdmin()) {
            binding.titleLoginTextView.setVisibility(View.INVISIBLE);
        } else {
            binding.manageUsersButton.setVisibility(View.GONE);
            binding.titleLoginTextView.setText(String.format("Welcome, %s", user.getUserName()));
        }
    }

    private void showLogoutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Logout?");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }

    private void logout() {
        loggedInUserId = LOGGED_OUT;
        updateSharedPreference();
        getIntent().putExtra(MAIN_ACTIVITY_USER_ID, loggedInUserId);

        startActivity(LoginActivity.loginIntentFactory((getApplicationContext())));
    }

    private void updateSharedPreference() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCE_FILE_KEY),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor= sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.PREFERENCE_USERID_KEY), loggedInUserId);
        sharedPrefEditor.apply();
    }

    static Intent mainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }

    private Monsters randomMonster() {
        Monsters monster1 = new Monsters("Firechic", "A flaming chickem.", 99, 3, 30.0,"Fire",10,4,3,5,1.0);
        Monsters monster2 = new Monsters("Mudslip", "A wet frog.", 99, 3, 30.0,"Water",10,5,3,4,1.0);
        Monsters monster3 = new Monsters("Woodcko", "A tree-like being.", 99, 3, 30.0,"Grass",10,4,5,3,1.0);
        Monsters monster4 = new Monsters("Arsus", "Unbeatable", 99, 99, 100.0, "Fire", 999, 99, 99, 99, 1.0);

        Monsters[] monsters = {monster1, monster2, monster3, monster4};

        Random random = new Random();
        int randomInt = random.nextInt(monsters.length);
        return monsters[randomInt];
    }

    private String battleLogic(Monsters monster, Monsters enemy) {
        Monsters first, second;
        if (monster.getAgility() > enemy.getAgility()) {
            first = monster;
            second = enemy;
        } else {
            first = enemy;
            second = monster;
        }

        StringBuilder result = new StringBuilder();

        while (monster.getHp() > 0 && enemy.getHp() > 0) {
            first.attack(second);
            if (enemy.getHp() <= 0) {
                monster.addExp(10);
                result.append("You won!! \nYour monster gained 10 exp.");
                break;
            }
            if (monster.getHp() <= 0) {
                monster.addExp(2);
                result.append("You lost!! \nYour monster gained 2 exp.");
                break;
            }
            second.attack(first);
            if (enemy.getHp() <= 0) {
                monster.addExp(10);
                result.append("You won!! \nYour monster gained 10 exp.");
                break;
            }
            if (monster.getHp() <= 0) {
                monster.addExp(2);
                result.append("You lost!! \nYour monster gained 2 exp.");
                break;
            }
        }
        return result.toString();
    }
}