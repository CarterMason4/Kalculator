package com.example.kalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
        // Identify the main views.
        calculTextView = findViewById(R.id.calculTextView);
        resultTextView = findViewById(R.id.resultTextView);
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

    // "Public" because a reference of the method is used in "activity_main.xml"
    // to execute the code.

    // Erase the operation and put it back to zero.

    public void clearAll(View view) {
        clearCalculTextView();
        clearResultTextView();
    }

    // Add the digits.

    public void addZero(View view) {
        addGivenValue("0");
    }

    public void addOne(View view) {
        addGivenValue("1");
    }

    public void addTwo(View view) {
        addGivenValue("2");
    }

    public void addThree(View view) {
        addGivenValue("3");
    }

    public void addFour(View view) {
        addGivenValue("4");
    }

    public void addFive(View view) {
        addGivenValue("5");
    }

    public void addSix(View view) {
        addGivenValue("6");
    }

    public void addSeven(View view) {
        addGivenValue("7");
    }

    public void addEight(View view) {
        addGivenValue("8");
    }

    public void addNine(View view) {
        addGivenValue("9");
    }

    // Add operations

    public void addPlus(View view) {
        addGivenValue("+");
    }

    public void addMinus(View view) {
        addGivenValue("-");
    }

    public void addMultiply(View view) {
        addGivenValue("*");
    }

    public void addDivide(View view) {
        addGivenValue("/");
    }

    public void addDot(View view) {
        addGivenValue(".");
    }

    public void makeOperation(View view) {

        String operation = calculTextView.getText().toString();

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

        // Variables used to store the values located left and right of the sign.
        String firstPart = "";
        String secondPart = "";

        for(int i = 0 ; i < operation.length() ; i++) {
            if(operation.charAt(i) == '+') {
                positionPlus = i;
                tempPosition = positionPlus;
                break;
            } else if(operation.charAt(i) == '-') {
                positionMinus = i;
                tempPosition = positionMinus;
                break;
            } else if(operation.charAt(i) == '*') {
                positionTimes = i;
                tempPosition = positionTimes;
                break;

            } else if(operation.charAt(i) == '/') {
                positionDivide = i;
                tempPosition = positionDivide;
                break;
            }
        }

        firstPart = operation.substring(0, tempPosition);
        secondPart = operation.substring(tempPosition + 1, operation.length());

        // The reason behing choosing doubles instead of integers is because we don't
        // want to trim the result. Plus, the result has to be accurate to be accepted.
        // Example : 5 / 2 is 2.5 using doubles and 2 using integers.

        double firstDouble = Double.parseDouble(firstPart);
        double secondDouble = Double.parseDouble(secondPart);

        // The sign which position is greater than zero, is the sign that's being used.

        if(positionPlus > 0) {
            result = firstDouble + secondDouble;
        } else if(positionMinus > 0) {
            result = firstDouble - secondDouble;
        } else if(positionTimes > 0) {
            result = firstDouble * secondDouble;
        } else if(positionDivide > 0) {
            result = firstDouble / secondDouble;
        }

        // The problem with using doubles instead of integers is that
        // the ".0" part is automatically added, even if the result is not decimal.
        // So it must be removed.

        // Store the position of the comma (.)
        int commaPosition = 0;

        // Convert the result to a string...
        String resultString = String.valueOf(result);

        // Circle through the string to find the position of the comma
        for(int i = 0 ; i < resultString.length() ; i++) {
            if(resultString.charAt(i) == '.') {
                commaPosition = i;
                break;
            }
        }

        //...Then simply trim the result.

        if(resultString.charAt(commaPosition + 1) == '0') {
          resultString = resultString.substring(0, commaPosition);
        }

        // Print the result
        resultTextView.setText(resultString);

        clearCalculTextView();
    }

    private void clearCalculTextView() {
        calculTextView.setText("");
    }

    private void clearResultTextView() {
        resultTextView.setText("");
    }

    public void printSomething() {
        System.out.print("Something");
    }
}