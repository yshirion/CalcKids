package com.example.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.example.calackids.ListCard;
import com.example.calackids.R;
import com.example.objects.Action;
import com.example.objects.Invest;
import com.example.objects.User;
import com.google.android.material.snackbar.Snackbar;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class InvestPresent extends StatePresent{
    private ArrayList<Invest> list;
    private ArrayList<Invest> toRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadList();
        //Only parent can delete invest
        if (app.currentParentUser != null)
            swipeCare();
    }

    // Load list of all investments belong to current user.
    public void loadList(){
        title.setText(getString(R.string.invest));
        toRemove = new ArrayList<Invest>();
        mAdapter.isInvest = true;
        User user  = app.currentChildUser;
        //get the list of actions according to current user, and create listcard for each action.
        try {
            app.actionService.getInvest(user.getId())
                    .enqueue(new Callback<List<Invest>>() {
                        @Override
                        public void onResponse(Call<List<Invest>> call, Response<List<Invest>> response) {
                            if (!response.isSuccessful()) finish();
                            list = (ArrayList<Invest>) response.body();
                            double sum =0;
                            for (Action action : list){
                                Invest invest = (Invest) action;
                                System.out.println(invest.getIid());
                                ListCard card = new ListCard(
                                        invest.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                        String.format("%.2f",invest.getCurrentAmount()),
                                        String.valueOf(invest.getInterest()),
                                        invest.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                sum += invest.getCurrentAmount();
                                actionCardList.add(card);
                            }
                            total.setText(String.format("%.3f",sum) + " $");
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        @Override
                        public void onFailure(Call<List<Invest>> call, Throwable t) {
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

    //Delete invest and and it to balance
    public void remove(int position) {
        Invest invest = list.get(position-1);
        ListCard item;
        toRemove.add((invest));
        list.remove(position-1);
        mAdapter.notifyItemRemoved(position);
        item = mAdapter.remove(position-1);
        fixTotal(invest.getCurrentAmount(), -1);
        Snackbar.make(mRecyclerView, "invest", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.add(position-1, invest);
                        mAdapter.notifyItemInserted(position);
                        mAdapter.readd(item, position-1);
                        toRemove.remove(toRemove.size()-1);
                        fixTotal(invest.getCurrentAmount(), 1);
                    }
                }).show();
    }

    @Override
    public void onBackPressed(){
        if (toRemove.size() == 0) finish();
        else {
            app.actionService.deleteInvest(toRemove).enqueue(new Callback<String>() {
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
}
