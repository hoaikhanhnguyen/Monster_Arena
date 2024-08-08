package com.example.monster_arena;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.User;
import com.example.monster_arena.databinding.ActivityBattleResultsBinding;

public class BattleResults extends AppCompatActivity {

    private ActivityBattleResultsBinding binding;
    private MonsterArenaRepository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleResultsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());

        binding.BattleDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BattleDetailsActivity.battleDetailsIntentFactory(getApplicationContext()));
            }
        });

        binding.BattleHistoryButtonInBatResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BattleHistoryActivity.battleHistoryIntentFactory(getApplicationContext()));
            }
        });

        //TODO: Button takes you to the Welcome Users page / DOES NOT WORK
        binding.ExitButtonInBatResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId()));
            }
        });
    }

    public static Intent battleResultsIntentFactory(Context context) {
        return new Intent(context, BattleResults.class);
    }
}