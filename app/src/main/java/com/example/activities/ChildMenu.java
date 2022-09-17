package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.Card;
import com.example.calackids.CardAdapter;
import com.example.calackids.R;


public class ChildMenu extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    CalcKidsApplication app = (CalcKidsApplication) getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_child);
        title = findViewById(R.id.userTitle);
        title.setText(setUserTitle());

        ArrayList<Card> cardsList = new ArrayList<Card>();

        cardsList.add(new Card(R.string.mission, R.drawable.mission,null));
        cardsList.add(new Card(R.string.message, R.drawable.message,null));
        cardsList.add(new Card(R.string.request, R.drawable.request,null));
        cardsList.add(new Card(R.string.balance, R.drawable.balance, Balance.class));
        cardsList.add(new Card(R.string.invest, R.drawable.invest,Loans.class));
        cardsList.add(new Card(R.string.loan, R.drawable.loan,Investments.class));
        //
        mRecyclerView = findViewById(R.id.idGVcard);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
       mAdapter = new CardAdapter(cardsList);
        //
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mAdapter);

    }

    private String setUserTitle() {
        String child;
        String parent = "";
        child = app.currentChildUser.getUser_name() + " id: " + app.currentChildUser.getId()
                + " Fid: " +app.currentChildUser.getFamily_id();
        if (app.currentParentUser != null) parent = ". by " + app.currentParentUser.getUser_name();

        return child + parent + ".";
    }


    public void onClick(View view){
        CardView c = (CardView) view;
        Intent intent = new Intent(this, (Class<?>) c.getTag());
        startActivity(intent);
    }

}