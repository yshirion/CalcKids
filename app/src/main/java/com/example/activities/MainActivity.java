package com.example.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.R;
import com.example.objects.User;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private Button signIn;
    private EditText userBox, passwordBox;
    CalcKidsApplication app; // The object of all app to manage al activities (in every activity we define it).

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = findViewById(R.id.signIn);
        userBox = findViewById(R.id.mainUser);
        passwordBox = findViewById(R.id.mainPassword);
    }

    //Set 'sign in' button.
    public void onClick(View v) {
        String userName = userBox.getText().toString().trim();
        String password = passwordBox.getText().toString().trim();
        //Check validation of the fields.
        if (userName.isEmpty() || password.isEmpty())
            Toast.makeText(getApplicationContext(), getString(R.string.fillAll), Toast.LENGTH_SHORT).show();
        else // Check with the server if this user exist, and his password OK.
            ifUserExist(userName,password);
    }

    //Check the details, if its correct, enter to his corresponding activity.
    public void ifUserExist(String userName, String password) {
        app = (CalcKidsApplication) getApplication();
        //Create User object base on the username and password, and check it with the server.
        app.userService
                .checkUser(userName,password)
                .enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), getString(R.string.erroruserpass), Toast.LENGTH_SHORT).show();
                }
                else {
                    Class<?> activity = defineActivity(response.body());//Define activity base on the type of user.
//                            "Welcome "+ response.body().getFirstName()
//                             + " " + response.body().getLast_name();
                    Toast.makeText(
                            getApplicationContext(),
                            getString(R.string.welcome,response.body().getFirstName(), response.body().getLast_name()),
                            Toast.LENGTH_SHORT).show();

                    // Reset the fields to the next time (and, of course, to avoid save the details of user).
                    userBox.setText("");
                    passwordBox.setText("");
                    // Enter to app base on type of user.
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

    //Define his activity- parent main or child main, base on the type of user.
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

    //Set the register buttons - enter to the corresponding register activity.
    public void registerClick(View view){
        Class<?> c = null;
        if (view.getId() == R.id.newFamily) c = Register_family.class;
        else c = Register.class;
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}
