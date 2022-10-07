package com.example.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.calackids.CalcKidsApplication;
import com.example.calackids.ListCard;
import com.example.calackids.ListCardAdapter;
import com.example.calackids.R;import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public abstract class StatePresent extends AppCompatActivity {

    protected CalcKidsApplication app;
//    SwipeRefreshLayout
    protected RecyclerView mRecyclerView;                 // RecyclerView and his tools:
    protected ListCardAdapter mAdapter;              // Adapter, manager, and list of objects
    protected RecyclerView.LayoutManager mLayoutManager;  // behind the cardviews(the parts of RecyclerView).
    protected ArrayList<ListCard> actionCardList;         //
    protected TextView total, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (CalcKidsApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        total = (TextView) findViewById(R.id.total);
        title = (TextView) findViewById(R.id.title_list_activity);
        initializeRecycler();
    }


    //Initialize the part of RecyclerView, and load the list of message from the server.
    private void initializeRecycler() {

        actionCardList = new ArrayList<ListCard>();
        mRecyclerView = findViewById(R.id.action_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(StatePresent.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListCardAdapter(actionCardList);

    }

    abstract void loadList();

    protected void swipeCare() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(getSwipe());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private ItemTouchHelper.Callback getSwipe() {
        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (position > 0)
                    remove(position);
            }
        };
        return simpleCallback;
    }

    protected void fixTotal(double toAdd, int sign){
        String cleanTotal = total.getText().toString().replace("$","").trim();
        double oldTotal = Double.parseDouble(cleanTotal);
        double newTotal = oldTotal + (toAdd * sign);
        total.setText( String.format("%.3f",newTotal) + " $");
    }
    abstract void remove(int position);

    //Set onClick to none, because we the card in those cases shouldn't be a button.
    public void onClick(View v){
    }
}
//    protected void onCreate(Bundle savedInstanceState) {
//        app = (CalcKidsApplication) getApplication();
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_state);
//        total = (TextView) findViewById(R.id.total);
//        title = (TextView) findViewById(R.id.title_list_activity);
//        initializeRecycler();
//    }

//    private void blabla() {
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(getSwipe());
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);
//    }
//
//    private ItemTouchHelper.Callback getSwipe() {
//        ItemTouchHelper.SimpleCallback simpleCallback =
//                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//                    @Override
//                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                        int position = viewHolder.getAdapterPosition();
//                        if (position > 0)
//                            remove(position);
//                    }
//                };
//        return simpleCallback;
//    }
//
//    private void remove(int position) {
//        Action action = list.get(position);
//        if (action instanceof Invest) toRemoveInvest.add((Invest) action);
//        else toRemoveLoan.add((Loan) action);
//        list.remove(position);
//        mAdapter.notifyItemRemoved(position);
//        mAdapter.remove(position);
//        Snackbar.make(mRecyclerView, action, Snackbar.LENGTH_LONG)
//                .setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        list.add(position, action);
//                    }
//                })
//    }
//
//    //Initialize the part of RecyclerView, and load the list of message from the server.
//    private void initializeRecycler() {
//
//        actionCardList = new ArrayList<ListCard>();
//        mRecyclerView = findViewById(R.id.action_list);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(StatePresent.this, LinearLayoutManager.VERTICAL, false);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new ListCardAdapter(actionCardList);
//
//        loadList();
//    }
//
//    // General method to decide which corresponding method use for current entry.
//    private void loadList() {
//        // Get the information from the previous activity, which activity should to be:
//        // for action, investment or loan.
//        Intent intent = getIntent();
//        String whichActivity = (String) intent.getSerializableExtra("whichActivity");
//
//        //Choise which is window we present now, and load list respectively.
//        if (whichActivity.equals(getString(R.string.balance))) loadListBalance();
//        else {
//            blabla();
//            if (whichActivity.equals(getString(R.string.invest))) loadListInvest();
//            else  loadListLoan();
//        }
//
//    }
//
//    // Load list of all actions belong to current user.
//    private void loadListBalance() {
//        title.setText(getString(R.string.balance));
//        // Set the title of list
////        actionCardList.add(new ListCard("date", "amount","type",""));
//        User user  = app.currentChildUser;
//        //get the list of actions according to current user, and create listcard for each action.
//        try {
//            app.actionService.getActions(user.getId())
//                    .enqueue(new Callback<List<Action>>() {
//                        @Override
//                        public void onResponse(Call<List<Action>> call, Response<List<Action>> response) {
//                            if (!response.isSuccessful()) finish();
//
//                            list = (ArrayList<Action>) response.body();
//                            for (Action act : list){
//                                ListCard card = new ListCard(
//                                        act.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                                        String.valueOf(act.getAmount()),
//                                        act.getType(),
//                                        "");
//                                card.isBalance = true;
//                                if (act.isPositive()) card.isPositive =true;
//                                actionCardList.add(card);
//                            }
//                            // Check the current balance of this user.
//                            app.userService.getById(user.getId()).enqueue(new Callback<User>() {
//                                @Override
//                                public void onResponse(Call<User> call, Response<User> response) {
//                                    if (response.isSuccessful()){
//                                        //if the answer is good, set the total of balance, and the list.
//                                        total.setText(String.format("%.3f",response.body().getBalance()) + " $");
//                                        mRecyclerView.setAdapter(mAdapter);
//                                    }
//                                }
//                                @Override
//                                public void onFailure(Call<User> call, Throwable t) {
//                                    Toast.makeText(
//                                            getApplicationContext(),
//                                            getString(R.string.networkerror),
//                                            Toast.LENGTH_LONG).show();
//                                }
//                            });
//                        }
//                        @Override
//                        public void onFailure(Call<List<Action>> call, Throwable t) {
//                            System.out.println(t);
//                        }
//                    });
//        }
//        catch (Exception e){
//            Toast.makeText(
//                    getApplicationContext(),
//                    getString(R.string.networkerror),
//                    Toast.LENGTH_LONG).show();
//            finish();
//        }
//    }
//
//    // Load list of all investments belong to current user.
//    private void loadListInvest(){
////        actionCardList.add(new ListCard("start", "amount","interest","end invest"));
//        title.setText(getString(R.string.invest));
//        toRemoveInvest = new ArrayList<Invest>();
//        User user  = app.currentChildUser;
//        //get the list of actions according to current user, and create listcard for each action.
//        try {
//            app.actionService.getInvest(user.getId())
//                    .enqueue(new Callback<List<Invest>>() {
//                        @Override
//                        public void onResponse(Call<List<Invest>> call, Response<List<Invest>> response) {
//                            if (!response.isSuccessful()) finish();
//                            list = (ArrayList<Invest>) response.body();
//                            double sum =0;
//                            for (Action action : list){
//                                Invest invest = (Invest) action;
//                                System.out.println(invest.getIid());
//                                ListCard card = new ListCard(
//                                        invest.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                                        String.format("%.2f",invest.getCurrentAmount()),
//                                        String.valueOf(invest.getInterest()),
//                                        invest.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//                                sum += invest.getCurrentAmount();
//                                actionCardList.add(card);
//                            }
//                            total.setText(String.format("%.3f",sum) + " $");
//                            mRecyclerView.setAdapter(mAdapter);
//                        }
//                        @Override
//                        public void onFailure(Call<List<Invest>> call, Throwable t) {
//                            Toast.makeText(
//                                    getApplicationContext(),
//                                    getString(R.string.networkerror),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });
//        }
//        catch (Exception e){
//            Toast.makeText(
//                    getApplicationContext(),
//                    getString(R.string.networkerror),
//                    Toast.LENGTH_LONG).show();
//            finish();
//        }
//    }
//
//    // Load list of all loans belong to current user.
//    private void loadListLoan() {
////        actionCardList.add(new ListCard("start", "amount","interest",""));
//        title.setText(getString(R.string.loan));
//        toRemoveLoan = new ArrayList<Loan>();
//        User user  = app.currentChildUser;
//        //get the list of actions according to current user, and create listcard for each action.
//        try {
//            app.actionService.getLoans(user.getId())
//                    .enqueue(new Callback<List<Loan>>() {
//                        @Override
//                        public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
//                            if (!response.isSuccessful()) finish();
//                            list = (ArrayList<Loan>) response.body();
//                            double sum =0;
//                            for (Action action : list){
//                                Loan loan = (Loan) action;
//                                ListCard card = new ListCard(
//                                        loan.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                                        String.format("%.2f",loan.getCurrentAmount()),
//                                        String.valueOf(loan.getInterest()),
//                                        "");
//                                sum += loan.getCurrentAmount();
//                                actionCardList.add(card);
//                            }
//                            total.setText(String.format("%.3f", sum) + " $");
//                            mRecyclerView.setAdapter(mAdapter);
//                        }
//                        @Override
//                        public void onFailure(Call<List<Loan>> call, Throwable t) {
//                            Toast.makeText(
//                                    getApplicationContext(),
//                                    getString(R.string.networkerror),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });
//        }
//        catch (Exception e){
//            Toast.makeText(
//                    getApplicationContext(),
//                    getString(R.string.networkerror),
//                    Toast.LENGTH_LONG).show();
//            finish();
//        }
//    }
//
//    //Set onClick to none, because we the card in those cases shouldn't be a button.
//    public void onClick(View v){
//    }
//
//    @Override
//    public void onBackPressed(){
//        if (toRemoveInvest != null)
//            removeFromDB(app.actionService.deleteInvest(toRemoveInvest));
//        else if (toRemoveLoan != null)
//            removeFromDB(app.actionService.deleteLoan(toRemoveLoan));
//        else finish();
//    }
//
//    private void removeFromDB(Call<String> stringCall){
//        stringCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        response.body(),
//                        Toast.LENGTH_LONG).show();
//                toRemoveLoan = null;
//                toRemoveInvest = null;
//                finish();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }