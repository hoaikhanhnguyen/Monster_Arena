package com.example.monster_arena;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monster_arena.databinding.ActivityBattleResultsBinding;

public class BattleResults extends AppCompatActivity {

    private ActivityBattleResultsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleResultsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_battle_results);

        //TODO: Button will take you to the Battle Results activity
        binding.BattleResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //TODO: Button takes you to the Battle History activity.
        binding.BattleHistoryButtonInBatResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //TODO: Button takes you to the Welcome Users page.
        binding.ExitButtonInBatResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), );
                startActivity(intent);
            }
        });
    }

    public static Intent battleResultsIntentFactory(Context context) {
        return new Intent(context, BattleResults.class);
    }
}