package com.example.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.calackids.CalcKidsApplication;
import com.example.calackids.ListCard;
import com.example.calackids.ListCardAdapter;
import com.example.calackids.R;
import com.example.objects.Message;
import com.example.objects.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Messages extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CalcKidsApplication app;
    private ArrayList<ListCard> messageCardList;
    private ArrayList<Message> messagesList;
    private boolean isModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (CalcKidsApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        initializeRecycler();
    }

    private void initializeRecycler() {

        messageCardList = new ArrayList<ListCard>();
        mRecyclerView = findViewById(R.id.messages_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(Messages.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListCardAdapter(messageCardList);

        loadList();
    }

    private void loadList() {
        messageCardList.add(new ListCard("date", "from","subject",""));
        User user;
        if (app.currentChildUser == null) user = app.currentParentUser;
        else user = app.currentChildUser;
        try {
            app.messageService.getMessageByDest(user.getId())
                    .enqueue(new Callback<List<Message>>() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                            if (!response.isSuccessful()) finish();

                            messagesList = (ArrayList<Message>) response.body();
                            for (Message message : messagesList){
                                ListCard card = new ListCard(
                                        message.getToday().toString(),
                                        message.getSender_name(),
                                        message.getSubject(),
                                        "",
                                        message);
                                card.isMessage = true;
                                if (message.isReaded()) card.isReaded = true;
                                messageCardList.add(card);
                            }
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        @Override
                        public void onFailure(Call<List<Message>> call, Throwable t) {
                            System.out.println(t);
                        }
                    });
        }
        catch (Exception e){
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.networkerror),
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View v){

        CardView c = (CardView) v;
        c.setBackgroundResource(R.drawable.rounded_white);
        ListCard mc = (ListCard) c.getTag();
        if (!mc.getMessage().isReaded()){
            mc.getMessage().setReaded(true);
            isModify = true;
        }
        Intent intent = new Intent(Messages.this, MessagePresent.class);
        intent.putExtra("content", mc);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        if (isModify){
            app.messageService.updateIsRead(messagesList).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else Toast.makeText(getApplicationContext(), "problem", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
        else finish();
    }

}