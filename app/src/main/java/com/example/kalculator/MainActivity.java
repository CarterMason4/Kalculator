package com.example.kalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView calculTextView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inintiateViews();
    }

    private void inintiateViews() {
        // Identify the text views.
        calculTextView = findViewById(R.id.calculTextView);
        resultTextView = findViewById(R.id.resultTextView);

        // Identify the buttons.

        // Operators
        Button clearAllButton = findViewById(R.id.clearAllButton);
        Button plusButton = findViewById(R.id.plusButton);
        Button minusButton = findViewById(R.id.minusButton);
        Button divideButton = findViewById(R.id.divideButton);
        Button multiplyButton = findViewById(R.id.multiplyButton);
        Button dotButton = findViewById(R.id.dotButton);
        Button equalButton = findViewById(R.id.equalButton);

        // Digits
        Button zeroButton = findViewById(R.id.zeroButton);
        Button oneButton = findViewById(R.id.oneButton);
        Button twoButton = findViewById(R.id.twoButton);
        Button threeButton = findViewById(R.id.threeButton);
        Button fourButton = findViewById(R.id.fourButton);
        Button fiveButton = findViewById(R.id.fiveButton);
        Button sixButton = findViewById(R.id.sixButton);
        Button sevenButton = findViewById(R.id.sevenButton);
        Button eightButton = findViewById(R.id.eightButton);
        Button nineButton = findViewById(R.id.nineButton);

        clearAllButton.setOnClickListener(view -> clearAll());
        equalButton.setOnClickListener(view -> makeOperation());
        plusButton.setOnClickListener(view -> addGivenValue("+"));
        minusButton.setOnClickListener(view -> addGivenValue("-"));
        divideButton.setOnClickListener(view -> addGivenValue("/"));
        multiplyButton.setOnClickListener(view -> addGivenValue("*"));
        dotButton.setOnClickListener(view -> addGivenValue("."));

        zeroButton.setOnClickListener(view -> addGivenValue("0"));
        oneButton.setOnClickListener(view -> addGivenValue("1"));
        twoButton.setOnClickListener(view -> addGivenValue("2"));
        threeButton.setOnClickListener(view -> addGivenValue("3"));
        fourButton.setOnClickListener(view -> addGivenValue("4"));
        fiveButton.setOnClickListener(view -> addGivenValue("5"));
        sixButton.setOnClickListener(view -> addGivenValue("6"));
        sevenButton.setOnClickListener(view -> addGivenValue("7"));
        eightButton.setOnClickListener(view -> addGivenValue("8"));
        nineButton.setOnClickListener(view -> addGivenValue("9"));

    }

    private void makeOperation() {
        String operation = calculTextView.getText().toString();

        if (Character.isDigit(operation.charAt(0))) {
            // Save the position of the sign : plus (+), minus (-), times (*) or divide (/)
            // Only one sign is going to be used during the operation.
            int positionPlus = 0;
            int positionMinus = 0;
            int positionTimes = 0;
            int positionDivide = 0;

            // Temporary variable used to store the position of the sign, whatever the sign may be.
            // It is used to separated the left and right part.
            int tempPosition = 0;

            // Result
            double result = 0.0;

            for (int i = 0; i < operation.length(); i++) {
                if (operation.charAt(i) == '+') {
                    positionPlus = i;
                    tempPosition = positionPlus;
                    break;
                } else if (operation.charAt(i) == '-') {
                    positionMinus = i;
                    tempPosition = positionMinus;
                    break;
                } else if (operation.charAt(i) == '*') {
                    positionTimes = i;
                    tempPosition = positionTimes;
                    break;
                } else if (operation.charAt(i) == '/') {
                    positionDivide = i;
                    tempPosition = positionDivide;
                    break;
                }
            }

            // Variables used to store the values located left and right of the sign.
            String firstPart = operation.substring(0, tempPosition);
            String secondPart = operation.substring(tempPosition + 1);

            // The reason behind choosing doubles instead of integers is because we don't
            // want to trim the result. Plus, the result has to be accurate to be accepted.
            // Example : 5 / 2 is 2.5 using doubles and 2 using integers.
            double firstDouble = Double.parseDouble(firstPart);
            double secondDouble = Double.parseDouble(secondPart);

            // The sign which position is greater than zero, is the sign that's being used.
            if (positionPlus > 0) {
                result = firstDouble + secondDouble;
            } else if (positionMinus > 0) {
                result = firstDouble - secondDouble;
            } else if (positionTimes > 0) {
                result = firstDouble * secondDouble;
            } else if (positionDivide > 0) {
                result = firstDouble / secondDouble;
            }

            // The "problem" with using doubles instead of integers is that
            // the ".0" part is automatically added, even if the result is not decimal.
            // So it must be removed.

            // Store the position of the comma (.)
            int commaPosition = 0;

            // Convert the result to a string...
            String resultString = String.valueOf(result);

            // Circle through the string to find the position of the comma
            for (int i = 0; i < resultString.length(); i++) {
                if (resultString.charAt(i) == '.') {
                    commaPosition = i;
                    break;
                }
            }

            //...Then simply trim the result.
            if (resultString.charAt(commaPosition + 1) == '0') {
                resultString = resultString.substring(0, commaPosition);
            }
            // Print the result
            resultTextView.setText(resultString);
        }
        clearCalculTextView();
    }

    // 1. Take the current value of the view.
    // 2. Using concatenation, the value of the pressed key is added to the string.
    // 3. The new value is shown.
    // "Private" because this function is only needed inside the class.
    private void addGivenValue(String digit) {
        String temp = calculTextView.getText().toString();
        String result = temp + digit;
        calculTextView.setText(result);
    }

    // Set the operation view to zero.
    private void clearCalculTextView() {
        calculTextView.setText("");
    }

    // Set the result view to zero.
    private void clearResultTextView() {
        resultTextView.setText("");
    }

    // Clear everything.
    private void clearAll() {
        clearCalculTextView();
        clearResultTextView();
    }
}