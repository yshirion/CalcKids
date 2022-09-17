package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.example.calackids.MainActivity;
import com.example.calackids.R;
import com.example.calackids.CalcKidsApplication;
import com.example.objects.User;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    TextInputEditText firstBox;
    TextInputEditText userBox;
    TextInputEditText passwordBox;
    TextInputEditText confPasswordBox;
    TextInputEditText familyIdBox;
    TextInputEditText familyNameBox;
    String first;
    String user;
    String password;
    String familyName;
    Button buttonSubmit;
    long familyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTextBox();

        buttonSubmit = findViewById(R.id.submit);

        buttonSubmit.setOnClickListener(view -> {
           try {
               stringHandle();
               CalcKidsApplication app = (CalcKidsApplication) getApplication();
               app.userService
                       .save(new User(first,user,password,familyName, familyId))
                       .enqueue(new Callback<String>() {
                           @Override
                           public void onResponse(Call<String> call, Response<String> response) {
                               if (response.body().toString().equals("saved")){
                                   Toast.makeText(
                                           getApplicationContext(),
                                           getString(R.string.loggupsuccess),
                                           Toast.LENGTH_LONG).show();
                                   Intent intent = new Intent(Register.this, MainActivity.class);
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
        password = passwordBox.getText().toString().trim();
        familyName = familyNameBox.getText().toString().trim();

        if (first.isEmpty() || user.isEmpty()
                ||familyName.isEmpty() || password.isEmpty())
            throw new Exception(getString(R.string.fillAll));

        if (!password.equals(confPasswordBox.getText().toString().trim()))
            throw new Exception(getString(R.string.passwordmtach));

        try {
            familyId = Long.parseLong(familyIdBox.getText().toString().trim());
        }
        catch (Exception e){
            throw new Exception(getString(R.string.familyidnum));
        }

    }

    private void setTextBox() {
        firstBox = findViewById(R.id.first_name);
        userBox = findViewById(R.id.user_name);
        passwordBox = findViewById(R.id.password);
        confPasswordBox = findViewById(R.id.conf_password);
        familyIdBox = findViewById(R.id.family_id_text);
        familyNameBox = findViewById(R.id.family_name);
    }
}