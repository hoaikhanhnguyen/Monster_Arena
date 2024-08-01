package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.User;
import com.example.monster_arena.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private MonsterArenaRepository repository;

    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        repository = MonsterArenaRepository.getRepository(getApplication());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verifyUser()) {
                    toastMaker("Invalid Username Or Password");
                }else {
                    Intent intent = MainActivity.mainActivityIntentFactory(getApplication(), 0);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean verifyUser() {
        String username = binding.userNameLoginEditText.getText().toString();
        if(username.isEmpty()) {
            toastMaker("Username Should Not Be Blank");
            return false;
        }
        user = repository.getUserUserByUserName(username);
        if(user != null) {
            String password = binding.passwordLoginEditText.getText().toString();
            if(password.equals(user.getPassword())) {
                return true;
            }else {
                toastMaker("Invalid Password");
                return false;
            }
        }
        toastMaker(String.format("No %s Found", username));
        return false;
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}