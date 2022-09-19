package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.R;
import com.example.objects.Family;

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
    Button saveChanges;
    final int MULTIPLIER = 100;

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

        saveChanges = findViewById(R.id.save_changes);

        initialize();
    }

    private void initialize() {

        getFamily();

        setOnSeekBar(loan, loanText, hidden_loan, R.string.loan_interest_title);
        setOnSeekBar(longInvest, longText, hidden_long, R.string.long_interest_title);
        setOnSeekBar(shortInvest, shortText, hidden_short, R.string.short_interest_title);


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

                loanText.setText(getString(R.string.loan_interest_title, interestLoan));
                longText.setText(getString(R.string.long_interest_title, interestLongTime));
                shortText.setText(getString(R.string.short_interest_title, interestShortTime));

                loan.setProgress((int) (interestLoan * MULTIPLIER), true);
                longInvest.setProgress((int) (interestLongTime * MULTIPLIER), true);
                shortInvest.setProgress((int) (interestShortTime * MULTIPLIER), true);

                hidden_loan.setText(String.valueOf(interestLoan));
                hidden_long.setText(String.valueOf(interestLongTime));
                hidden_short.setText(String.valueOf(interestShortTime));
            }

            @Override
            public void onFailure(Call<Family> call, Throwable t) {
                System.out.println("problem");
            }
        });
    }

    private void setOnSeekBar(SeekBar seekBar, TextView textView, TextView hidden, int stringRes) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            double changes;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changes = (double) progress / MULTIPLIER;
                textView.setText(getString(stringRes, (double) progress / MULTIPLIER));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hidden.setText(String.valueOf(changes));
            }
        });
    }

    public void onClick(View view){
        app = (CalcKidsApplication) getApplication();
        currentFamily.setLoanInterest(Double.parseDouble(hidden_loan.getText().toString()));
        currentFamily.setInvestLongInterest(Double.parseDouble(hidden_long.getText().toString()));
        currentFamily.setInvestShortInterest(Double.parseDouble(hidden_short.getText().toString()));

        System.out.println(""+currentFamily.getLoanInterest());
        System.out.println(""+currentFamily.getInvestLongInterest());
        System.out.println(""+currentFamily.getInvestShortInterest());

        app.familyService.update(currentFamily).enqueue(new Callback<Family>() {
            @Override
            public void onResponse(Call<Family> call, Response<Family> response) {
                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.savedChanges),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Family> call, Throwable t) {

            }
        });
    }

}