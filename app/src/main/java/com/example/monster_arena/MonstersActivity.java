package com.example.monster_arena;

import java.util.Random;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class MonstersActivity extends AppCompatActivity implements Monster_RecyclerViewInterface {

    private static final int MAX_RAND = 10; //max value for randomly generated numbers
    private ActivityMonstersBinding binding;
    private MonsterArenaRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMonstersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monsters);

        getRoomData();
   }

    private void getRoomData() {
        RecyclerView recyclerView = findViewById(R.id.monsterListViewTable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Monsters> monsters = repository.getAllMonsters();
        Monster_RecyclerViewAdapter adapter = new Monster_RecyclerViewAdapter((Context) this,monsters, (Monster_RecyclerViewInterface) this);

        recyclerView.setAdapter(adapter);
    }

    static Intent manageMonsterActivityIntentFactory(Context context){
        return new Intent(context, MonstersActivity.class);
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

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertBuilder.create().show();
    }

    private void deleteMonster(int position) {
        List<Monsters> monsters = repository.getAllMonsters();
        String monsterName = monsters.get(position).getName();
        Monsters monster = repository.getMonsterByName(monsterName);
        repository.delete(monster);
        Toast.makeText(this, String.format("%s was deleted", monsterName), Toast.LENGTH_LONG).show();
        startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), monster.getId()));
    }

    private void createMonster(){
        int rand = new Random().nextInt(MAX_RAND);
    }
}
