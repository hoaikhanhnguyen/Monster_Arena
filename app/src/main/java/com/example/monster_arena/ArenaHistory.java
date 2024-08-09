package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.Arena;
import com.example.monster_arena.databinding.ActivityArenaHistoryBinding;


import java.util.List;

public class ArenaHistory extends AppCompatActivity {
    private ActivityArenaHistoryBinding binding;
    private MonsterArenaRepository repository;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArenaHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());
        userId = getIntent().getIntExtra("USER_ID", -1);

        repository.getAllArenas(userId).observe(this, new Observer<List<Arena>>() {
            @Override
            public void onChanged(List<Arena> arenas) {
                if (arenas != null && !arenas.isEmpty()) {
                    binding.arenaHistoryTextView.setText(arenas.toString());
                } else {
                    binding.arenaHistoryTextView.setText("No Arenas found.");
                }
            }
        });

        binding.returnButtonArenaHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ArenaTypes.arenaTypesIntentFactory(getApplicationContext(), userId));
            }
        });
    }

    public static Intent arenaHistoryIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ArenaHistory.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }
}

