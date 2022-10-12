package com.example.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.example.calackids.ListCard;
import com.example.calackids.R;
import com.example.objects.Action;
import com.example.objects.Loan;
import com.example.objects.User;
import com.google.android.material.snackbar.Snackbar;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LoanPresent extends StatePresent{
    //    SwipeRefreshLayout
    private ArrayList<Loan> list;
    private ArrayList<Loan> toRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadList();
        //Only parent can delete loan
        if (app.currentParentUser != null)
            swipeCare();
    }

    // Load list of all loans belong to current user.
    public void loadList() {
        title.setText(getString(R.string.loan));
        toRemove = new ArrayList<Loan>();
        mAdapter.isLoan = true;
        User user  = app.currentChildUser;
        //get the list of actions according to current user, and create listcard for each action.
        try {
            app.actionService.getLoans(user.getId())
                    .enqueue(new Callback<List<Loan>>() {
                        @Override
                        public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
                            if (!response.isSuccessful()) finish();
                            list = (ArrayList<Loan>) response.body();
                            double sum =0;
                            for (Action action : list){
                                Loan loan = (Loan) action;
                                ListCard card = new ListCard(
                                        loan.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                        String.format("%.2f",loan.getCurrentAmount()),
                                        String.valueOf(loan.getInterest()),
                                        "");
                                sum += loan.getCurrentAmount();
                                actionCardList.add(card);
                            }
                            total.setText(String.format("%.3f", sum) + " $");
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        @Override
                        public void onFailure(Call<List<Loan>> call, Throwable t) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    getString(R.string.networkerror),
                                    Toast.LENGTH_LONG).show();
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

    //Delete loan and and it to balance
    public void remove(int position) {
        Loan loan = list.get(position);
        ListCard item;
        toRemove.add((loan));
        list.remove(position);
        mAdapter.notifyItemRemoved(position);
        item = mAdapter.remove(position);
        fixTotal(loan.getCurrentAmount(), -1);
        Snackbar.make(mRecyclerView, "loan", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.add(position, loan);
                        mAdapter.notifyItemInserted(position);
                        mAdapter.readd(item, position);
                        fixTotal(loan.getCurrentAmount(), 1);
                    }
                }).show();
    }

    @Override
    public void onBackPressed(){
        app.actionService.deleteLoan(toRemove).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(
                        getApplicationContext(),
                        response.body(),
                        Toast.LENGTH_LONG).show();
                toRemove = null;
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
