package com.example.calackids;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.activities.ChildMenu;
import com.example.activities.ParentMenu;
import com.example.activities.Register;
import com.example.activities.Register_family;
import com.example.objects.User;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button signIn;
    private EditText userBox, passwordBox;
    private String message ="";
    CalcKidsApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = findViewById(R.id.signIn);
        userBox = findViewById(R.id.mainUser);
        passwordBox = findViewById(R.id.mainPassword);
    }

    public void onClick(View v) {
        String userName = userBox.getText().toString().trim();
        String password = passwordBox.getText().toString().trim();
        if (userName.isEmpty() || password.isEmpty())
            Toast.makeText(getApplicationContext(), getString(R.string.fillAll), Toast.LENGTH_SHORT).show();
        else
            ifUserExist(userName,password);
    }

    public void ifUserExist(String userName, String password) {
        app = (CalcKidsApplication) getApplication();
        app.userService
                .checkUser(userName,password)
                .enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), getString(R.string.erroruserpass), Toast.LENGTH_SHORT).show();
                }
                else {
                    Class<?> activity = defineActivity(response.body());
                    message = "Welcome "+ response.body().getFirstName()
                             + " " + response.body().getLast_name();
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    userBox.setText("");
                    passwordBox.setText("");
                    Intent intent = new Intent(MainActivity.this, activity);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.networkerror), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Class<?> defineActivity(User user) {
        app = (CalcKidsApplication) getApplication();
        if (user.isParent()) {
            app.currentParentUser = user;
            return ParentMenu.class;
        }
        else {
            app.currentChildUser = user;
            return ChildMenu.class;
        }
    }

    public void registerClick(View view){
        Class<?> c = null;
        if (view.getId() == R.id.newFamily) c = Register_family.class;
        else c = Register.class;
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

}
