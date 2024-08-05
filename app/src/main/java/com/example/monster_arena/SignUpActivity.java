package com.example.monster_arena;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.example.monster_arena.database.MonsterArenaRepository;
import com.example.monster_arena.database.entities.User;
import com.example.monster_arena.databinding.ActivityLoginBinding;
import com.example.monster_arena.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    private MonsterArenaRepository repository;
    private final int loggedInUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        repository = MonsterArenaRepository.getRepository(getApplication());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.signupCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });

        binding.signupCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpCancel();
            }
        });
    }

    private void signUpUser() {
        String username = binding.userNameCreationEditText.getText().toString();
        String password = binding.passwordCreationEditText.getText().toString();
        String confirmPassword = binding.confirmPasswordCreationEditText.getText().toString();

        if(username.isEmpty()) {
            toastMaker("Username should not be blank");
            return;
        }

        if(password.isEmpty() || confirmPassword.isEmpty()) {
            toastMaker("Password cannot be empty");
            return;
        }

        if(!password.equals(confirmPassword)) {
            toastMaker("Passwords must match");
            return;
        }

        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if(user != null) {
                toastMaker(String.format("%s is not an available username.", username));
                binding.userNameCreationEditText.setSelection(0);
            }else {
                repository.insertUser(new User(username, password));
                toastMaker("User Account Successfully Created");
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), loggedInUserId));
            }
        });
    }

    public void signUpCancel() {
        startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), loggedInUserId));
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    static Intent signUpActivityIntentFactory(Context context) {
        return new Intent(context, SignUpActivity.class);
    }
}