package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.User;
import com.example.monster_arena.databinding.ActivityManageUserBinding;

import java.util.List;

public class ManageUserActivity extends AppCompatActivity {
    private ActivityManageUserBinding binding;

    private MonsterArenaRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityManageUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = MonsterArenaRepository.getRepository(getApplication());
        assert repository != null;

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getRoomData();
    }

    private void getRoomData() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<User> users = repository.getAllUsers();
        User_RecyclerViewAdapter adapter = new User_RecyclerViewAdapter(this, users);
        recyclerView.setAdapter(adapter);
    }

    static Intent manageUserActivityIntentFactory(Context context) {
        return new Intent(context, ManageUserActivity.class);
    }
}