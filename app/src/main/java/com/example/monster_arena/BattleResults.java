package com.example.monster_arena;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.User;
import com.example.monster_arena.databinding.ActivityBattleResultsBinding;

public class BattleResults extends AppCompatActivity {

    private ActivityBattleResultsBinding binding;
    private MonsterArenaRepository repository;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleResultsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());

        userId = getIntent().getIntExtra("USER_ID", -1);

        String battleResult = getIntent().getStringExtra("battleResult");
        binding.ExpResults.setText(battleResult);

        binding.BattleDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BattleDetailsActivity.battleDetailsIntentFactory(getApplicationContext(), userId);
                intent.putExtra("battleResult", battleResult);
                startActivity(intent);
            }
        });

        binding.BattleHistoryButtonInBatResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BattleHistoryActivity.battleHistoryIntentFactory(getApplicationContext(), userId);
                intent.putExtra("battleResult", battleResult);
                startActivity(intent);
            }
        });

        binding.ExitButtonInBatResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), userId));
            }
        });
    }

    public static Intent battleResultsIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, BattleResults.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }
}