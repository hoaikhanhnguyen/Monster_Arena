package com.example.monster_arena;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monster_arena.database.entities.User;
import com.example.monster_arena.databinding.ActivityBattleResultsBinding;

public class BattleResults extends AppCompatActivity {

    private ActivityBattleResultsBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleResultsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_battle_results);

        //TODO: Button will take you to the Battle Results activity
        binding.BattleDetailsButton.setOnClickListener(new View.OnClickListener() {
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
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId()));
            }
        });
    }

    public static Intent battleResultsIntentFactory(Context context) {
        return new Intent(context, BattleResults.class);
    }
}