package com.example.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.calackids.CalcKidsApplication;
import com.example.calackids.R;
import com.example.objects.Message;
import com.example.objects.User;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CreateMessage extends AppCompatActivity {
    CalcKidsApplication app;
    //Spinner and its adapter to load the list of possible destinations.
    private Spinner spinner;
    private ArrayAdapter<User> adapter;
    private ArrayList<User> children;
    //
    private EditText subject, textField;
    private Button close;
    private ImageButton send;

    private User receiver, sender;
    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (CalcKidsApplication) getApplication();
        setContentView(R.layout.activity_create_message);
        subject = (EditText) findViewById(R.id.subject);
        textField = (EditText) findViewById(R.id.textField);

        defineAsSubActivity();//Define activity to be small and on parent.
        setNamesList();
        setSpinner();
        setClose();
        setSend();
    }

    //Set the send button to create message object base on the details in activity.
    private void setSend() {
        send = (ImageButton) findViewById(R.id.send);

            send.setOnClickListener(v -> {
                if (textField.getText().toString().trim().equals("") ||   //
                        subject.getText().toString().trim().equals("")) { //can't send empty message or empty subject.
                    Toast.makeText(getApplicationContext(), getString(R.string.fillAll), Toast.LENGTH_SHORT).show();
                }
                else {
                    message = new Message(
                            sender.getId(),
                            receiver.getId(),
                            subject.getText().toString(),
                            textField.getText().toString(),
                            false);
                    sendMessage();
                    finish();
                }
            }
        );
    }

    private void setClose() {
        close = (Button) findViewById(R.id.close);
        close.setOnClickListener(v -> {
            finish();
        });
    }

    //Define the list of spinner, and his adapter, and what should happen when is chose some element.
    private void setSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<User>(CreateMessage.this, android.R.layout.simple_spinner_item, children);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                receiver = (User) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    //Set children list by the list was passed by the previous activity.
    private void setNamesList() {
        Intent intent = getIntent();
        children = (ArrayList<User>) intent.getSerializableExtra("childrenList");

        //Define the sender base on recognize the type of user(parent or child).
        if (children.size() > 0 && children.get(0).isParent())
            sender = app.currentChildUser;
        else
            sender = app.currentParentUser;

    }

    private void defineAsSubActivity() {
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int width = display.widthPixels;
        int height = display.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.7));
    }

    //Send the message created by send button(setSend method) and check the result.
    private void sendMessage() {
        app.messageService.saveMessage(message)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body().equals("saved")){
                            Toast.makeText(
                                    getApplicationContext(),
                                    response.body(),
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    R.string.not_saved,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }

}