package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.CardAdapter;
import com.example.calackids.MenuCard;
import com.example.calackids.R;
import com.example.objects.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChildMenu extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    CalcKidsApplication app;
    ArrayList<MenuCard> cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (CalcKidsApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        title = findViewById(R.id.userTitle);
        title.setText(setUserTitle());
        Button b = new Button(this);

        initializeMenu();
    }

    private void initializeMenu() {
        cardsList = new ArrayList<MenuCard>();

        //Add all cards with their title, icon, and destination activity.
        cardsList.add(new MenuCard(getString(R.string.mission), R.drawable.mission, BlankforWhile.class));
        cardsList.add(new MenuCard(getString(R.string.message), R.drawable.message, CreateMessage.class));
        cardsList.add(new MenuCard(getString(R.string.request), R.drawable.request, BlankforWhile.class));
        cardsList.add(new MenuCard(getString(R.string.balance), R.drawable.balance, Balance.class));
        cardsList.add(new MenuCard(getString(R.string.invest), R.drawable.invest,Loans.class));
        cardsList.add(new MenuCard(getString(R.string.loan), R.drawable.loan,Investments.class));
        cardsList.add(new MenuCard(getString(R.string.send), R.drawable.send_message, CreateMessage.class));

        //Change activity for view from parent.
        if (app.currentParentUser != null){
            cardsList.remove(cardsList.size()-1);
            cardsList.add(new MenuCard(getString(R.string.createinvest), R.drawable.file, BlankforWhile.class));
            cardsList.add(new MenuCard(getString(R.string.createloan), R.drawable.file, BlankforWhile.class));
        }

        //Load cardviews and put on activity.
        mRecyclerView = findViewById(R.id.idGVcard);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardAdapter(cardsList);
        //
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mAdapter);
    }

    //Unique result for press on each card.
    public void onClick(View view) {
        CardView c = (CardView) view;
        MenuCard mc = (MenuCard) c.getTag();
        if (mc.getActivity() == CreateMessage.class)
            defineParent(mc);
        else {
            Intent intent = new Intent(this, mc.getActivity());
            startActivity(intent);
        }
    }

    public String setUserTitle(){
        app = (CalcKidsApplication) getApplication();
        String child = getString(R.string.hello,
                                app.currentChildUser.getFirstName(),
                                app.currentChildUser.getUser_name(),
                                app.currentChildUser.getId(),
                                app.currentChildUser.getFamily_id());
        String parent = "";
        if (app.currentParentUser != null)
            parent = getString(R.string.parent, app.currentParentUser.getUser_name());

        return child + parent + ".";
    }

    @Override
    public void onBackPressed(){
        app = (CalcKidsApplication) getApplication();
        app.currentChildUser = null;
        finish();
    }

    private void defineParent(MenuCard mc) {
        app.userService
                .getParent(app.currentChildUser.getFamily_id())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            ArrayList<User> array = new ArrayList<User>();
                            array.add(response.body());
                            Intent intent = new Intent(ChildMenu.this, mc.getActivity());
                            intent.putExtra("childrenList", array);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    getString(R.string.cantFind),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });
    }
}