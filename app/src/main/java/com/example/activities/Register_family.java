package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.MainActivity;
import com.example.calackids.R;
import com.example.objects.User;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_family extends AppCompatActivity {
    TextInputEditText firstBox;
    TextInputEditText familyBox;
    TextInputEditText userBox;
    TextInputEditText passwordBox;
    TextInputEditText confPasswordBox;
    String first;
    String familyName;
    String user;
    String password;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_family);
        setTextBox();

        buttonSubmit = findViewById(R.id.submit_family);

        buttonSubmit.setOnClickListener(view -> {
            try {
                stringHandle();
                CalcKidsApplication app = (CalcKidsApplication) getApplication();
                app.userService
                        .saveParent(new User(first,familyName,user,password))
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body().toString().equals("saved")){
                                    Toast.makeText(
                                            getApplicationContext(),
                                            getString(R.string.loggupsuccess),
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Register_family.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            response.body(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                System.out.println(t.getMessage());

                                Toast.makeText(
                                        getApplicationContext(),
                                        "Error: " + t.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            catch (Exception e){
                Toast.makeText(
                        getApplicationContext(),
                        e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void stringHandle() throws Exception{
        first = firstBox.getText().toString().trim();
        user = userBox.getText().toString().trim();
        familyName = familyBox.getText().toString().trim();
        password = passwordBox.getText().toString().trim();

        if (first.isEmpty() || user.isEmpty()
            ||familyName.isEmpty() || password.isEmpty())
            throw new Exception(getString(R.string.fillAll));

        if (!password.equals(confPasswordBox.getText().toString().trim()))
            throw new Exception(getString(R.string.passwordmtach));
    }

    private void setTextBox() {
        firstBox = findViewById(R.id.first_name_family);
        userBox = findViewById(R.id.user_name_family);
        passwordBox = findViewById(R.id.password_family);
        confPasswordBox = findViewById(R.id.conf_password_family);
        familyBox = findViewById(R.id.family_name_family);
    }
}