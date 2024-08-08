package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.Battle;
import com.example.monster_arena.databinding.ActivityBattleDetailsBinding;

public class BattleDetailsActivity extends AppCompatActivity {

    private ActivityBattleDetailsBinding binding;
    private MonsterArenaRepository repository;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());

        userId = getIntent().getIntExtra("USER_ID", -1);

        assert repository != null;
        repository.getRecentBattle().observe(this, new Observer<Battle>() {
            @Override
            public void onChanged(Battle battle) {
                if (battle != null) {
                    binding.mostRecentBattleDetails.setText(battle.toString());
                }
            }
        });

        binding.returnButtonBatDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BattleResults.battleResultsIntentFactory(getApplicationContext(), userId));
            }
        });
    }

    public static Intent battleDetailsIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, BattleDetailsActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }
}