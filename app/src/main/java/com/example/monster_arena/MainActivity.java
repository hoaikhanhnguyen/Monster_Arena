package com.example.monster_arena;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monster_arena.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    public static final String TAG = "ARENA_LOG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.chooseArenaDisplayText.setMovementMethod(new ScrollingMovementMethod());

        //Click listeners for all Arena Page Buttons
        binding.arena1button.setOnClickListener(v -> handleArenaButtonClick("Arena 1"));
        binding.arena2button.setOnClickListener(v -> handleArenaButtonClick("Arena 2"));
        binding.arena3button.setOnClickListener(v -> handleArenaButtonClick("Arena 3"));
        binding.arenaBackHomeButton.setOnClickListener(v -> handleBackHomeButtonClick());
        binding.arenaBackPreviousPageButton.setOnClickListener(v -> handleBackPreviousPageButtonClick());
    }

    // Common method to handle arena button clicks
    private void handleArenaButtonClick(String arena) {
        Log.d(TAG, arena + " button clicked");
        getInformationFromDisplay();
        updateDisplay(arena);
    }

    // Method to handle "Home" button click
    private void handleBackHomeButtonClick() {
        Log.d(TAG, "Home button clicked");
        // Implement the logic to navigate to the home page or activity
    }


    private void handleBackPreviousPageButtonClick() {
        Log.d(TAG, "Back button clicked");

    }

    // Method to update display based on selected arena
    private void updateDisplay(String arena) {
        // Implement logic to update the display based on the selected arena
        binding.chooseArenaDisplayText.setText("You selected " + arena);
        Log.d(TAG, "Display updated for " + arena);
    }

    // Placeholder for method to get information from display
    private void getInformationFromDisplay() {
        // Implement logic to get information from display
        Log.d(TAG, "Get information from display called");
    }
}