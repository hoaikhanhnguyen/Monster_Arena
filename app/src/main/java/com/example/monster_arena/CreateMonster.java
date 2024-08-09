package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.Monsters;
import com.example.monster_arena.databinding.ActivityMonstersBinding;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CreateMonster extends AppCompatActivity implements Monster_RecyclerViewInterface{

    private static final int MAX_RAND = 10; //max value for randomly generated numbers
    private ActivityMonstersBinding binding;
    private MonsterArenaRepository repository;
    private int loggedInUser = -1;
    static int monsterId;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMonstersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = MonsterArenaRepository.getRepository(getApplication());

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_FILE_KEY),
                Context.MODE_PRIVATE);
        loggedInUser = sharedPreferences.getInt(getString(R.string.PREFERENCE_USERID_KEY), -1);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_monsters);

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

    static Intent createMonsterActivityIntentFactory(Context context, int loggedInUser){
        Intent intent = new Intent(context, CreateMonster.class);
        intent.putExtra("User_Id", loggedInUser);
        return intent;
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
        int rand = new Random().nextInt(3);

        if (rand == 1){ // random fire
            int randStat = new Random().nextInt(MAX_RAND);
            String newMonsterName = "CharMonster";
            Monsters monster = new Monsters(newMonsterName, "Fire type", loggedInUser, randStat, 10.0, "Fire Type", randStat+2, randStat, randStat+1, randStat, 10);
            repository.insertMonsters(monster);
        } else if (rand ==2){   // random water monster
            int randStat = new Random().nextInt(MAX_RAND);
            String newMonsterName = "HoserDude";
            Monsters monster = new Monsters(newMonsterName, "Water type", loggedInUser, randStat, 10.0, "Water Type", randStat+2, randStat, randStat+1, randStat, 10);
            repository.insertMonsters(monster);
        }else {     // random grass
            int randStat = new Random().nextInt(MAX_RAND);
            String newMonsterName = "Bulbguy";
            Monsters monster = new Monsters(newMonsterName, "Grass type", loggedInUser, randStat, 10.0, "Grass Type", randStat+2, randStat, randStat+1, randStat, 10);
            repository.insertMonsters(monster);
        }
    }


    @Override
    public void onItemClick(int position) {

    }
}
