package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.Arena;
import com.example.monster_arena.databinding.ActivityArenaTypesBinding;

public class ArenaTypes extends AppCompatActivity {

    private ActivityArenaTypesBinding binding;
    private MonsterArenaRepository repository;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArenaTypesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());

        userId = getIntent().getIntExtra("USER_ID", -1);

        createDefaultArenas();
        binding.fireArenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), userId);
                intent.putExtra("Arena Selected", "Fire Arena");
                startActivity(intent);
            }
        });

        binding.waterArenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), userId);
                intent.putExtra("Arena Selected", "Water Arena");
                startActivity(intent);
            }
        });

        binding.grassArenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), userId);
                intent.putExtra("Arena Selected", "Grass Arena");
                startActivity(intent);
            }
        });

    }

    private void createDefaultArenas() {
        // Create Arena object with all types of arenas
        Arena defaultArena = new Arena("Fire Arena", "Grass Arena", "Water Arena");
        repository.insertNewArena(defaultArena);
    }

    public static Intent arenaTypesIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ArenaTypes.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }
}
