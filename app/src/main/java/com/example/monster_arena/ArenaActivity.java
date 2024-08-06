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

import com.example.monster_arena.databinding.ActivityArenaSelectionBinding;
import com.example.monster_arena.databinding.ActivityBattleResultsBinding;

public class ArenaActivity extends AppCompatActivity {
    private ActivityArenaSelectionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArenaSelectionBinding.inflate(getLayoutInflater());setContentView(R.layout.activity_arena_selection);


        //TODO: Button will take you to Arena 1
        binding.arena1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //TODO: Button takes you to Arena 2
        binding.arena2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //TODO: Button takes you to Arena 3
        binding.arena3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //TODO: Button takes you to the Welcome Users page.
        binding.returnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //TODO: Button takes you to previous page
        binding.returnToPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }




    public static Intent arenaActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, ArenaActivity.class);
        return intent;
    }
}