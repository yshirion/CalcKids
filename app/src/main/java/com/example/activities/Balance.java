package com.example.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.ListCard;
import com.example.calackids.ListCardAdapter;
import com.example.calackids.R;
import com.example.objects.Action;
import com.example.objects.User;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Balance extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CalcKidsApplication app;
    private ArrayList<Action> actionList;
    private ArrayList<ListCard> actionCardList;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (CalcKidsApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        total = (TextView) findViewById(R.id.total);

        initializeRecycler();
    }

    private void initializeRecycler() {

        actionCardList = new ArrayList<ListCard>();
        mRecyclerView = findViewById(R.id.action_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(Balance.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListCardAdapter(actionCardList);


        loadList();
    }

    private void loadList() {
        User user;
        if (app.currentParentUser == null)
            user = app.currentChildUser;
        else user = app.currentParentUser;
        try {
            app.actionService.getActions(user.getId())
                    .enqueue(new Callback<List<Action>>() {
                        @Override
                        public void onResponse(Call<List<Action>> call, Response<List<Action>> response) {
                                if (!response.isSuccessful()) finish();

                                actionList = (ArrayList<Action>) response.body();
                                for (Action act : actionList){
                                    ListCard card = new ListCard(
                                            act.getStart().toString(),
                                            String.valueOf(act.getAmount()),
                                            act.getType(),
                                            "");
                                    if (act.isPositive()) card.positive =true;
                                    actionCardList.add(card);
                                }
                                app.userService.getById(app.currentChildUser.getId()).enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        if (response.isSuccessful()){
                                            total.setText(String.valueOf(response.body().getBalance())+ " $");
                                            mRecyclerView.setAdapter(mAdapter);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {

                                    }
                                });
                        }

                        @Override
                        public void onFailure(Call<List<Action>> call, Throwable t) {
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
    public void onClick(View v){

    }

}