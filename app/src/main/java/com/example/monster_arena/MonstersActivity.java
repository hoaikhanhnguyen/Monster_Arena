package com.example.monster_arena;

import java.util.Locale;
import java.util.Random;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monster_arena.database.entities.Monsters;
import com.example.monster_arena.databinding.ActivityMonstersBinding;
import com.example.monster_arena.database.MonsterArenaRepository;
import java.util.List;
import java.util.stream.Collectors;

public class MonstersActivity extends AppCompatActivity implements Monster_RecyclerViewInterface {

    private static final int MAX_RAND = 10; //max value for randomly generated numbers
    private ActivityMonstersBinding binding;
    private MonsterArenaRepository repository;
    private int loggedInUser = -1;
    static int monsterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMonstersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = MonsterArenaRepository.getRepository(getApplication());

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_FILE_KEY),
                Context.MODE_PRIVATE);
        loggedInUser = sharedPreferences.getInt(getString(R.string.PREFERENCE_USERID_KEY), -1);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monsters);

        getRoomData();
   }

    private void getRoomData() {
        RecyclerView recyclerView = findViewById(R.id.monsterListViewTable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Monsters> useMonsters = repository.getAllMonsters().stream()
                .filter(monsters -> monsters.getUser_id() == loggedInUser)
                .collect(Collectors.toList());
        Monster_RecyclerViewAdapter adapter = new Monster_RecyclerViewAdapter((Context) this,useMonsters, (Monster_RecyclerViewInterface) this, loggedInUser);
        recyclerView.setAdapter(adapter);
    }

    static Intent manageMonsterActivityIntentFactory(Context context, int loggedInUser){
                Intent intent = new Intent(context, MonstersActivity.class);
                intent.putExtra("User_Id", loggedInUser);
        return intent;
    }

    public void onItemClick(int position) {
        showMonsterDialog(position);
    }

    private void showMonsterDialog(int position) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MonstersActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Manage Monster Roster");

        alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteMonster(position);
            }
        });


        alertBuilder.setNegativeButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {selectMonster(position);
            }
        });

        alertBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();

    }

    private void selectMonster(int position) {
        List<Monsters> monsters = repository.getAllMonsters();
        String monsterName = monsters.get(position).getName();
        int userId = monsters.get(position).getUser_id();
        Monsters monster = repository.getMonsterByName(monsterName);
        monsterId = monsters.get(position).getId();
        repository.getMonsterById(monster);
        Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), userId);
        intent.putExtra("Selected_Monster",monsterId);
        Toast.makeText(this, String.format(Locale.US,"%s was Selected, with id %d", monsterName,monsterId), Toast.LENGTH_LONG).show();
        startActivity(intent);
        //startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), loggedInUser));
    }

    private void deleteMonster(int position) {
        List<Monsters> monsters = repository.getAllMonsters();
        String monsterName = monsters.get(position).getName();
        Monsters monster = repository.getMonsterByName(monsterName);
        repository.delete(monster);
        Toast.makeText(this, String.format("%s was deleted", monsterName), Toast.LENGTH_LONG).show();
        //startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), loggedInUser));
    }


    private void createMonster(){
        int rand = new Random().nextInt(MAX_RAND);
    }
}
