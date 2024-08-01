package com.example.monster_arena;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monster_arena.databinding.ActivityMonstersBinding;
import com.example.monster_arena.database.MonsterArenaRepository;


public class MonstersActivity extends AppCompatActivity {

    ActivityMonstersBinding binding;

    public static final String TAG = "RSG_MONSTERS_LOG";

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonstersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MonsterArenaRepository repository = MonsterArenaRepository.getRepository(getApplication());
    }
}
