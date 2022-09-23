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
public class Register extends AppCompatActivity {

    TextInputEditText firstBox;
    TextInputEditText userBox;
    TextInputEditText passwordBox;
    TextInputEditText confPasswordBox;
    TextInputEditText familyIdBox;
    TextInputEditText familyNameBox;
    Button buttonSubmit;

    String first;
    String user;
    String password;
    String familyName;
    long familyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTextBox();

        setClick();
    }

    // Submit button check the user with the server, and save the user if the all correct.
    private void setClick() {
        buttonSubmit = findViewById(R.id.submit);

        buttonSubmit.setOnClickListener(view -> {
            //try for the each problem, from the server or from the fields, and give corresponding message
            try {
                stringHandle();
                CalcKidsApplication app = (CalcKidsApplication) getApplication();
                // Build User base on the details and check with server.
                app.userService
                        .save(new User(first,user,password,familyName, familyId))
                        .enqueue(new Callback<String>() {

                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body().toString().equals("saved")){
                                    Toast.makeText(
                                            getApplicationContext(),
                                            getString(R.string.logupsuccess),
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

    //Take the text from the field, check validation, before send to server.
    private void stringHandle() throws Exception{
        first = firstBox.getText().toString().trim();
        user = userBox.getText().toString().trim();
        password = passwordBox.getText().toString().trim();
        familyName = familyNameBox.getText().toString().trim();

        //The fields not empty.
        if (first.isEmpty() || user.isEmpty()
                ||familyName.isEmpty() || password.isEmpty())
            throw new Exception(getString(R.string.fillAll));

        //matching in passwords.(ignore the spaces)
        if (!password.equals(confPasswordBox.getText().toString().trim()))
            throw new Exception(getString(R.string.passwordmtach));

        //If family id is number (if not the method Long.parseLong throw exception).
        try {
            familyId = Long.parseLong(familyIdBox.getText().toString().trim());
        }
        catch (Exception e){
            throw new Exception(getString(R.string.familyidnum));
        }

    }

    private void setTextBox() {
        firstBox = findViewById(R.id.subject);
        userBox = findViewById(R.id.user_name);
        passwordBox = findViewById(R.id.password);
        confPasswordBox = findViewById(R.id.conf_password);
        familyIdBox = findViewById(R.id.family_id_text);
        familyNameBox = findViewById(R.id.family_name);
    }
}