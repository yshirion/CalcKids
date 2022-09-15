package com.example.activities;

import android.os.Bundle;

import com.example.calackids.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.calackids.databinding.ActivityBalanceBinding;

public class Balance extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityBalanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
    }

}