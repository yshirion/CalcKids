package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.calackids.R;
import com.example.calackids.CalacKidsApplication;
import com.example.objects.User;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputEditText first = findViewById(R.id.first_name);
        TextInputEditText userName = findViewById(R.id.user_name);
        TextInputEditText password = findViewById(R.id.password);
        TextInputEditText family = findViewById(R.id.family_id_text);

        Button buttonSubmit = findViewById(R.id.submit);

        buttonSubmit.setOnClickListener(view -> {
            CalacKidsApplication app = (CalacKidsApplication) getApplication();
            app.userService.save(
                    new User(
                            "ddd",
                            "a",
                            "1w2w",
                            "fklasdjl")
            ).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    app.currentUser = response.body();
                    startActivity(new Intent(Register.this, ChildMenu.class));
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Error: " + t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });


        });


    }
}