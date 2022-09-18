package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.R;
import com.example.objects.Family;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends AppCompatActivity {

    CalcKidsApplication app;
    Family currentFamily;
    SeekBar loan,longInvest, shortInvest;
    TextView loanText,longText,shortText;
    TextView hidden_loan, hidden_long, hidden_short;
    double interestLoan, interestLongTime, interestShortTime;
    final int MULTIPLIER = 100;
    final String DEFAULT_LOAN = "Percent of loan interest:  ";
    final String DEFAULT_LONG = "Percent of long investment:  ";
    final String DEFAULT_SHORT = "Percent of short investment:  ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        loan = findViewById(R.id.seekBarLoan);
        longInvest = findViewById(R.id.seekBarLong);
        shortInvest = findViewById(R.id.seekBarShort);

        loanText = findViewById(R.id.loanInterestText);
        longText = findViewById(R.id.longInvestText);
        shortText = findViewById(R.id.shortInvestText);

        hidden_loan = findViewById(R.id.hidden_loan);
        hidden_long = findViewById(R.id.hidden_long);
        hidden_short = findViewById(R.id.hidden_short);


        initial();
        setOnSeekBar(loan, loanText, hidden_loan, DEFAULT_LOAN);
        setOnSeekBar(longInvest, longText, hidden_long, DEFAULT_LONG);
        setOnSeekBar(shortInvest, shortText, hidden_short, DEFAULT_SHORT);
    }

    private void initial() {

        getFamily();
//        interestLoan = currentFamily.getLoanInterest();
//        interestLongTime = currentFamily.getInvestLongInterest();
//        interestShortTime = currentFamily.getInvestShortInterest();
//
//        loanText.setText(DEFAULT_LOAN + interestLoan);
//        loanText.setText(DEFAULT_LONG + interestLongTime);
//        shortText.setText(DEFAULT_SHORT + interestShortTime);


//        loan.setProgress((int) interestLoan * MULTIPLIER);
//        longInvest.setProgress((int) interestLong * MULTIPLIER);
//        shortInvest.setProgress((int) interestShort * MULTIPLIER);
    }

    private void getFamily(){
        app = (CalcKidsApplication) getApplication();
        app.familyService.findById(app.currentParentUser.getFamily_id()).enqueue(new Callback<Family>() {
            @Override
            public void onResponse(Call<Family> call, Response<Family> response) {
                if (!response.isSuccessful()) System.out.println("problem");
                currentFamily = response.body();
                interestLoan = currentFamily.getLoanInterest();
                interestLongTime = currentFamily.getInvestLongInterest();
                interestShortTime = currentFamily.getInvestShortInterest();

                loanText.setText(DEFAULT_LOAN + interestLoan);
                longText.setText(DEFAULT_LONG + interestLongTime);
                shortText.setText(DEFAULT_SHORT + interestShortTime);
            }

            @Override
            public void onFailure(Call<Family> call, Throwable t) {
                System.out.println("problem");
            }
        });
    }

    private void setOnSeekBar(SeekBar seekBar, TextView textView, TextView hidden, String defaultText) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String s = String.valueOf((double) progress / MULTIPLIER);
                hidden.setText(s);
                textView.setText(defaultText + s);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}