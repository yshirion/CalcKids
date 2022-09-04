package com.example.calackids;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ChildMenu extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_child);

        ArrayList<Card> cardsList = new ArrayList<Card>();

        cardsList.add(new Card("aaa", R.drawable.mission,0));
        cardsList.add(new Card("sss", R.drawable.message,0));
        cardsList.add(new Card("ddd", R.drawable.request,0));
        cardsList.add(new Card("fff", R.drawable.balance,0));
        cardsList.add(new Card("ggg", R.drawable.invest,0));
        cardsList.add(new Card("vvv", R.drawable.loan,0));
//
        mRecyclerView = findViewById(R.id.idGVcard);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardAdapter(cardsList);
//
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        }

    }

