package com.example.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.example.calackids.ListCard;
import com.example.calackids.R;
import com.example.objects.Action;
import com.example.objects.User;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ActionPresent extends StatePresent{

    private ArrayList<Action> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadList();
    }

    // Load list of all actions belong to current user.
    public void loadList() {
        title.setText(getString(R.string.balance));
        // Set the title of list
        User user  = app.currentChildUser;
        //get the list of actions according to current user, and create listcard for each action.
        try {
            app.actionService.getActions(user.getId())
                    .enqueue(new Callback<List<Action>>() {
                        @Override
                        public void onResponse(Call<List<Action>> call, Response<List<Action>> response) {
                            if (!response.isSuccessful()) finish();

                            list = (ArrayList<Action>) response.body();
                            for (Action act : list){
                                ListCard card = new ListCard(
                                        act.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                        String.valueOf(act.getAmount()),
                                        act.getType(),
                                        "");
                                card.isBalance = true;
                                if (act.isPositive()) card.isPositive =true;
                                actionCardList.add(card);
                            }
                            // Check the current balance of this user.
                            app.userService.getById(user.getId()).enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    if (response.isSuccessful()){
                                        //if the answer is good, set the total of balance, and the list.
                                        total.setText(String.format("%.3f",response.body().getBalance()) + " $");
                                        mRecyclerView.setAdapter(mAdapter);
                                    }
                                }
                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            getString(R.string.networkerror),
                                            Toast.LENGTH_LONG).show();
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

    @Override
    void remove(int position) {}

}
