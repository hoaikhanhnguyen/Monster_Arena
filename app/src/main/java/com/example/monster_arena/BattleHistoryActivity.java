package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.Battle;
import com.example.monster_arena.databinding.ActivityBattleHistoryBinding;

import java.util.List;

public class BattleHistoryActivity extends AppCompatActivity {

    private ActivityBattleHistoryBinding binding;
    private MonsterArenaRepository repository;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());
        userId = getIntent().getIntExtra("USER_ID", -1);

        repository.getAllBattles(userId).observe(this, new Observer<List<Battle>>() {
            @Override
            public void onChanged(List<Battle> battles) {
                binding.battleHistoryList.setText("");
                if (battles != null && !battles.isEmpty()) {
                    StringBuilder batHistory = new StringBuilder();
                    for (Battle battle : battles) {
                        batHistory.append(battles.toString());
                    }
                    binding.battleHistoryList.setText(batHistory.toString());
                } else {
                    binding.battleHistoryList.setText("No battles found.");
                }
            }
        });

        binding.returnButtonBattleHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BattleResults.battleResultsIntentFactory(getApplicationContext(), userId));
            }
        });
    }

    public static Intent battleHistoryIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, BattleHistoryActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }

}