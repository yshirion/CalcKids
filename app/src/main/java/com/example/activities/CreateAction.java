package com.example.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.calackids.CalcKidsApplication;
import com.example.calackids.R;
import com.example.objects.Action;
import com.example.objects.Invest;
import com.example.objects.Loan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CreateAction extends AppCompatActivity {

    CalcKidsApplication app;
    private Button actionButton, investButton, loanButton, close;
    private EditText  actionAmount, investAmount, loanAmount;
    boolean length, positive;

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

        actionAmount = (EditText) findViewById(R.id.amount_action);
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

    public void actionClick(View v){
        double amount;
        String type;
        if (!isDouble(actionAmount.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), getString(R.string.invalidValue), Toast.LENGTH_SHORT).show();
        }
        else {
            amount = Double.parseDouble(actionAmount.getText().toString().trim());
            if (positive) type = "income";
            else type = "expense";

            Action action = new Action(positive,type,amount,app.currentChildUser.getId());
            send(action);
        }
    }

    public void investClick(View v){
        double amount;
        String type;
        if (!isDouble(investAmount.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), getString(R.string.invalidValue), Toast.LENGTH_SHORT).show();
        }
        else {
            amount = Double.parseDouble(investAmount.getText().toString().trim());
            Action action = new Invest(false, "invest", amount, app.currentChildUser.getId(), length);
            send(action);
        }
    }

    public void loanClick(View v){
        double amount;
        if (!isDouble(loanAmount.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), getString(R.string.invalidValue), Toast.LENGTH_SHORT).show();
        }
        else {
            amount = Double.parseDouble(loanAmount.getText().toString().trim());
            Action action = new Loan(true, "loan", amount, app.currentChildUser.getId());
            send(action);
        }
    }
    public void closeClick(View v) {
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
                if (response.isSuccessful() && response.body().equals("saved")) {
                    Toast.makeText(
                            getApplicationContext(),
                            response.body(),
                            Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.not_saved,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t);
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
    public void radioActionClick(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_income:
                if (checked)
                    positive = true;
                    break;
            case R.id.radio_expense:
                if (checked)
                    positive = false;
                    break;
        }
    }

    public void radioInvestClick(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_long:
                if (checked)
                    length = true;
                    break;
            case R.id.radio_short:
                if (checked)
                    length = false;
                    break;
        }
    }
}