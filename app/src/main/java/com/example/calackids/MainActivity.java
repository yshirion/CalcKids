package com.example.calackids;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activities.ChildMenu;
import com.example.calackids.databinding.ActivityMainBinding;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Button signin;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        signin = (Button) findViewById(R.id.signIn);
        setContentView(R.layout.activity_main);


    }


    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, ChildMenu.class);
//        intent.setClass(MainActivity.this, Register_family.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

}