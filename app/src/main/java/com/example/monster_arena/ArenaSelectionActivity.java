package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.Arena;
import com.example.monster_arena.databinding.ActivityArenaSelectionBinding;

import java.util.List;

public class ArenaSelectionActivity extends AppCompatActivity {
    private ActivityArenaSelectionBinding binding;
    private MonsterArenaRepository repository;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArenaSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());
        userId = getIntent().getIntExtra("USER_ID", -1);

        // Load arenas and display them
        loadArenas();
    }

    private void loadArenas() {
        repository.getAllArenas().observe(this, arenas -> {
            if (arenas != null && !arenas.isEmpty()) {
                StringBuilder arenaList = new StringBuilder();
                for (Arena arena : arenas) {
                    arenaList.append("Fire Arena: ").append(arena.getFireArena()).append("\n");
                    arenaList.append("Grass Arena: ").append(arena.getGrassArena()).append("\n");
                    arenaList.append("Water Arena: ").append(arena.getWaterArena()).append("\n\n");
                }
                binding.arenaSelectionTextView.setText(arenaList.toString());

                binding.arenaSelectionTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Example: selecting the first arena's fire type
                        Arena selectedArena = arenas.get(0);
                        Toast.makeText(ArenaSelectionActivity.this, "Selected Fire Arena: " + selectedArena.getFireArena(), Toast.LENGTH_SHORT).show();

                        // Proceed with the selected arena (you might want to pass it back or use it in a battle)
                    }
                });
            } else {
                binding.arenaSelectionTextView.setText("No arenas available.");
            }
        });
    }

    public static Intent arenaSelectionIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ArenaSelectionActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }
}