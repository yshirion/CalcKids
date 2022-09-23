package com.example.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.example.calackids.CalcKidsApplication;
import com.example.calackids.R;
import com.example.objects.User;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Register_family extends AppCompatActivity {
    TextInputEditText firstBox;
    TextInputEditText familyBox;
    TextInputEditText userBox;
    TextInputEditText passwordBox;
    TextInputEditText confPasswordBox;
    Button buttonSubmit;

    String first;
    String familyName;
    String user;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_family);
        setTextBox();

        setSubmit();
    }
    // Submit button check the user with the server, and save the user if the all correct.
    private void setSubmit() {
        buttonSubmit = findViewById(R.id.submit_family);

        buttonSubmit.setOnClickListener(view -> {
            //try for the each problem, from the server or from the fields, and give corresponding message
            try {
                stringHandle();
                CalcKidsApplication app = (CalcKidsApplication) getApplication();
                // Build User base on the details and check with server.
                app.userService
                        .saveParent(new User(first,familyName,user,password))
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body().toString().equals("saved")){
                                    Toast.makeText(
                                            getApplicationContext(),
                                            getString(R.string.logupsuccess),
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

        //the fields empty.
        if (first.isEmpty() || user.isEmpty()
            ||familyName.isEmpty() || password.isEmpty())
            throw new Exception(getString(R.string.fillAll));
        //matching in passwords.(ignore the spaces)
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