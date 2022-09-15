//package com.example.calackids;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//
//public class ChildMenu extends AppCompatActivity {
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_child);
//
//        }
//
//    }
//
package com.example.activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calackids.Card;
import com.example.calackids.CardAdapter;
import com.example.calackids.R;
import com.example.calackids.CalacKidsApplication;
import com.example.objects.User;


public class ChildMenu extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_child);

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

        User currentUser = ((CalacKidsApplication) getApplication()).currentUser;
        Toast.makeText(
                getApplicationContext(),
                "Hello " + currentUser.getFirstName() + " " + currentUser.getLastName(),
                Toast.LENGTH_SHORT).show();

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // nothing
            }
        });
    }
    public void onClick(View view){
        CardView c = (CardView) view;
        Intent intent = new Intent(this, (Class<?>) c.getTag());
        startActivity(intent);
    }

}