package com.example.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;
import com.example.calackids.ListCard;
import com.example.calackids.R;

@RequiresApi(api = Build.VERSION_CODES.O)
//Activity to show the message.
public class MessagePresent extends AppCompatActivity {

    Button close;
    TextView from, subject, content;
    ListCard messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_present);

        defineAsSubActivity(); //Define the activity small and on his parent activity.
        setViews();
        setText();
        setClose();
    }

    private void setViews() {
        from = (TextView) findViewById(R.id.from);
        subject = (TextView) findViewById(R.id.subject_present);
        content = (TextView) findViewById(R.id.text_present);
        close = (Button) findViewById(R.id.close_message);
    }

    //Set the details of message base on the object that passed from the previous activity.
    private void setText() {
        Intent intent = getIntent();
        messageView = (ListCard) intent.getSerializableExtra("content");
        from.setText(messageView.getAmount_from());
        subject.setText(messageView.getSubject_type());
        content.setText(messageView.getMessage().getText());
    }

    private void defineAsSubActivity() {
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int width = display.widthPixels;
        int height = display.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.7));
    }

    private void setClose() {
        close.setOnClickListener(v -> {
            finish();
        });
    }
}