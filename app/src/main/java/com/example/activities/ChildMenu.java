package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.CardAdapter;
import com.example.calackids.MenuCard;
import com.example.calackids.R;


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
        setContentView(R.layout.activity_menu_child);
        title = findViewById(R.id.userTitle);
        title.setText(setUserTitle());
        Button b = new Button(this);

        initializeMenu();
    }

    private void initializeMenu() {
        cardsList = new ArrayList<MenuCard>();

        //Add all cards with their title, icon, and destination activity.
        cardsList.add(new MenuCard(getString(R.string.mission), R.drawable.mission, BlankforWhile.class));
        cardsList.add(new MenuCard(getString(R.string.message), R.drawable.message,Messages.class));
        cardsList.add(new MenuCard(getString(R.string.request), R.drawable.request, BlankforWhile.class));
        cardsList.add(new MenuCard(getString(R.string.balance), R.drawable.balance, Balance.class));
        cardsList.add(new MenuCard(getString(R.string.invest), R.drawable.invest,Loans.class));
        cardsList.add(new MenuCard(getString(R.string.loan), R.drawable.loan,Investments.class));
        cardsList.add(new MenuCard(getString(R.string.send), R.drawable.send_message, null));

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
    public void onClick(View view){
        CardView c = (CardView) view;
        MenuCard mc = (MenuCard) c.getTag();
        Intent intent = new Intent(this, mc.getActivity());
        startActivity(intent);
    }
    public String setUserTitle(){
        app = (CalcKidsApplication) getApplication();
        String child;
        String parent = "";
        child = app.currentChildUser.getUser_name() + " id: " + app.currentChildUser.getId()
                + " Fid: " +app.currentChildUser.getFamily_id();
        if (app.currentParentUser != null) parent = ". by " + app.currentParentUser.getUser_name();

        return child + parent + ".";
    }

    @Override
    public void onBackPressed(){
        app = (CalcKidsApplication) getApplication();
        app.currentChildUser = null;
        finish();
    }
}