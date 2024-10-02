package com.example.nathanperezmortgagecalc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare UI components
    private EditText etPrincipal, etInterestRate, etAmortizationPeriod;
    private TextView tvResult;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etPrincipal = findViewById(R.id.et_principal);
        etInterestRate = findViewById(R.id.et_interest_rate);
        etAmortizationPeriod = findViewById(R.id.et_amortization_period);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        // Set onClickListener to calculate button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMonthlyPayment();
            }
        });
    }

    // Method to calculate the monthly mortgage payment
    private void calculateMonthlyPayment() {
        try {
            // Retrieve user inputs
            double principal = Double.parseDouble(etPrincipal.getText().toString());
            double interestRate = Double.parseDouble(etInterestRate.getText().toString()) / 100 / 12; // Convert annual rate to monthly
            int amortizationPeriod = Integer.parseInt(etAmortizationPeriod.getText().toString()) * 12; // Convert years to months

            // Calculate monthly payment using formula taken from https://en.wikipedia.org/wiki/Mortgage_calculator
            double monthlyPayment = (principal * interestRate * Math.pow(1 + interestRate, amortizationPeriod))
                    / (Math.pow(1 + interestRate, amortizationPeriod) - 1);

            // Display the result
            tvResult.setText(String.format("Monthly Payment: $%.2f", monthlyPayment));
        } catch (NumberFormatException e) {
            tvResult.setText("Please enter valid numbers.");
        }
    }
}
