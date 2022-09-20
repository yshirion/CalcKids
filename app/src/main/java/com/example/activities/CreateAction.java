package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.R;
import com.example.objects.Action;
import com.example.objects.Invest;
import com.example.objects.Loan;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAction extends AppCompatActivity {

    CalcKidsApplication app;
    private Button actionButton, investButton, loanButton, close;
    private EditText actionType, actionAmount, investType, investAmount, loanAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_action);
        app = (CalcKidsApplication) getApplication();
        defineAsSubActivity();
        setAll();
    }

    private void setAll() {

        close = (Button) findViewById(R.id.close_actions);
        actionButton = (Button) findViewById(R.id.execute_action);
        investButton = (Button) findViewById(R.id.execute_invest);
        loanButton = (Button) findViewById(R.id.execute_loan);

        actionType = (EditText) findViewById(R.id.type_of);
        actionAmount = (EditText) findViewById(R.id.amount_action);
        investType = (EditText) findViewById(R.id.length);
        investAmount = (EditText) findViewById(R.id.amount_invest);
        loanAmount = (EditText) findViewById(R.id.amount_loan);
    }

    private void defineAsSubActivity() {
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int width = display.widthPixels;
        int height = display.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.7));
    }

    private void actionClick(View v){
        double amount;
        String type = actionType.getText().toString().trim().toLowerCase();
        if ((!type.equals("income") && !type.equals("expense")) ||
                !isDouble(actionAmount.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), getString(R.string.invalidValue), Toast.LENGTH_SHORT).show();
        }
        else {
            amount = Double.parseDouble(actionType.getText().toString().trim());
            boolean positive = type.equals("income")? true : false;
            Action action = new Action(positive,type,amount,new Date(),app.currentChildUser.getId());
            send(action);
        }
    }

    private void investClick(View v){
        double amount;
        String type = investType.getText().toString().trim().toLowerCase();
        if ((!type.equals("long") && !type.equals("short")) ||
                !isDouble(actionAmount.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), getString(R.string.invalidValue), Toast.LENGTH_SHORT).show();
        }
        else {
            boolean length = type.equals("long")? true : false;
            amount = Double.parseDouble(actionType.getText().toString().trim());
            Action action = new Invest(false, "invest", amount,
                    new Date(), app.currentChildUser.getId(), length);
            send(action);
        }
    }
    private void loanClick(View v){
        double amount;
        if (!isDouble(actionAmount.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), getString(R.string.invalidValue), Toast.LENGTH_SHORT).show();
        }
        else {
            amount = Double.parseDouble(actionAmount.getText().toString().trim());
            Action action = new Loan(true, "loan", amount, new Date(), app.currentChildUser.getId());
            send(action);
        }
    }
    private void closeClick(View v) {
            finish();
    }

    private void send(Action action) {
        Call<String> call;
        if (action instanceof Invest)
            call = app.actionService.saveInvest((Invest) action);
        else if (action instanceof Loan)
            call = app.actionService.saveLoan((Loan) action);
        else{
            call = app.actionService.saveAction(action);
        }
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private boolean isDouble(String s){
        try {
            Double.parseDouble(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}