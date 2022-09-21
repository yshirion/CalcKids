package com.example.activities;

import android.content.Intent;
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
import com.example.objects.Invest;
import com.example.objects.Loan;
import com.example.objects.User;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class StatePresent extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CalcKidsApplication app;
    private ArrayList<Action> actionList;
    private ArrayList<Invest> investList;
    private ArrayList<Loan> loansList;
    private ArrayList<ListCard> actionCardList;
    private TextView total, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (CalcKidsApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        total = (TextView) findViewById(R.id.total);
        title = (TextView) findViewById(R.id.title_list_activity);

        initializeRecycler();
    }

    private void initializeRecycler() {

        actionCardList = new ArrayList<ListCard>();
        mRecyclerView = findViewById(R.id.action_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(StatePresent.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListCardAdapter(actionCardList);

        loadList();
    }

    private void loadList() {
        Intent intent = getIntent();
        String whichActivity = (String) intent.getSerializableExtra("whichActivity");

        //Choise which is window we present now, and load list respectively.
        if (whichActivity.equals(getString(R.string.balance))) loadListBalance();
        else if (whichActivity.equals(getString(R.string.invest))) loadListInvest();
        else  loadListLoan();
    }

    private void loadListBalance() {
        actionCardList.add(new ListCard("date", "amount","type",""));
        title.setText(getString(R.string.balance));
        User user  = app.currentChildUser;
        try {
            app.actionService.getActions(user.getId())
                    .enqueue(new Callback<List<Action>>() {
                        @Override
                        public void onResponse(Call<List<Action>> call, Response<List<Action>> response) {
                                if (!response.isSuccessful()) finish();

                                actionList = (ArrayList<Action>) response.body();
                                for (Action act : actionList){
                                    ListCard card = new ListCard(
                                            act.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                            String.valueOf(act.getAmount()),
                                            act.getType(),
                                            "");
                                    card.isBalance = true;
                                    if (act.isPositive()) card.isPositive =true;
                                    actionCardList.add(card);
                                }
                                app.userService.getById(user.getId()).enqueue(new Callback<User>() {
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

    private void loadListInvest(){
        actionCardList.add(new ListCard("start", "amount","interest","end invest"));
        title.setText(getString(R.string.invest));
        User user  = app.currentChildUser;
        try {
            app.actionService.getInvest(user.getId())
                    .enqueue(new Callback<List<Invest>>() {
                        @Override
                        public void onResponse(Call<List<Invest>> call, Response<List<Invest>> response) {
                            if (!response.isSuccessful()) finish();

                            investList = (ArrayList<Invest>) response.body();
                            double sum =0;
                            for (Invest invest : investList){
                                ListCard card = new ListCard(
                                        invest.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                        String.format("%.2f",invest.getCurrentAmount()),
                                        String.valueOf(invest.getInterest()),
                                        invest.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                sum += invest.getCurrentAmount();
                                actionCardList.add(card);
                            }
                            total.setText(String.format("%.4f",sum) + " $");
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        @Override
                        public void onFailure(Call<List<Invest>> call, Throwable t) {
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

    private void loadListLoan() {
        actionCardList.add(new ListCard("start", "amount","interest",""));
        title.setText(getString(R.string.loan));
        User user  = app.currentChildUser;
        try {
            app.actionService.getLoans(user.getId())
                    .enqueue(new Callback<List<Loan>>() {
                        @Override
                        public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
                            if (!response.isSuccessful()) finish();

                            loansList = (ArrayList<Loan>) response.body();
                            double sum =0;
                            for (Loan loan : loansList){
                                ListCard card = new ListCard(
                                        loan.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                        String.format("%.2f",loan.getCurrentAmount()),
                                        String.valueOf(loan.getInterest()),
                                        "");
                                sum += loan.getCurrentAmount();
                                actionCardList.add(card);
                            }
                            total.setText(String.format("%.4f", sum) + " $");
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        @Override
                        public void onFailure(Call<List<Loan>> call, Throwable t) {
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