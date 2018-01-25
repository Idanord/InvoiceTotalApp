package com.example.will.invoicetotalapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity implements TextView.OnEditorActionListener {

    //declare instance variables for the widgets

    private EditText inputEditText;
    private TextView percentTextView;
    private TextView discountTextView;
    private TextView totalTextView;

    private String subtotalString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the widgets for the R class
        inputEditText = (EditText) findViewById(R.id.inputEditText);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        discountTextView = (TextView) findViewById(R.id.discountTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        inputEditText.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();

        return false;
    }

    private void calculateAndDisplay(){
        //get subtotal
        subtotalString = inputEditText.getText().toString();
        float subtotal;
        //convert subtotal to sting
        if(subtotalString.equals("")){
            subtotal = 0f;
        } else {
            subtotal = Float.parseFloat(subtotalString);
        }

        //get the discount percent
        float discountPercent = 0f;

        //set discount percent
        if(subtotal >= 200){
            discountPercent = .2f;
        } else if (subtotal >=100){
            discountPercent = .1f;
        } else {
            discountPercent = 0;
        }

        //calculate the discount
        float discountAmount = subtotal * discountPercent;
        float total = subtotal - discountAmount;

        //display the data on the layout
        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(discountPercent));

        //display the discount amount
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        discountTextView.setText(currency.format(discountAmount));

        //display the total
        totalTextView.setText(currency.format(total));
    }
}
