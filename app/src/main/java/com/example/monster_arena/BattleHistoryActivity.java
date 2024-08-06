package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monster_arena.databinding.ActivityBattleHistoryBinding;

public class BattleHistoryActivity extends AppCompatActivity {

    private ActivityBattleHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //TODO: Button returns user to the Battle Results page.
        binding.returnButtonBattleHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static Intent battleHistoryIntentFactory(Context context) {
        Intent intent = new Intent(context, BattleHistoryActivity.class);
        return intent;
    }

}