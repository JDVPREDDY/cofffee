package com.example.android.cofffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cofffee.R;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
            protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("dum1",quantity);
        outState.putString("dum2",backup);
    }

    @Override
            protected void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);
        quantity = inState.getInt("dum1");
        display(quantity);
        backup = inState.getString("dum2");
        displayMessage(backup);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 0;
    String backup;

    public void submitOrder(View view) {

        EditText nameOb = (EditText)findViewById(R.id.name_id);
        String name = nameOb.getText().toString();
        if (name != null && !name.isEmpty()) {


        CheckBox isWhipped = (CheckBox) findViewById(R.id.whipped_cream_id);
        boolean whip = isWhipped.isChecked();
        CheckBox isChoco = (CheckBox) findViewById(R.id.chocolate_id);
        boolean choco = isChoco.isChecked();
        int baseprice = 5;
        if (whip) {
            baseprice = baseprice + 1;
        }
        if (choco) {
            baseprice = baseprice + 2;
        }
        int price = baseprice * quantity;
        String pricemessage = "Name: " + name;
        pricemessage = pricemessage + "\nAdd whipped cream?" + whip;
        pricemessage = pricemessage + "\nAdd Chocolate?" + choco;
        pricemessage = pricemessage + "\ntotal: $" + price;
        pricemessage = pricemessage + "\nThank you!";
        displayMessage(pricemessage);
        backup = pricemessage;
    }
    else
        {
            Toast.makeText(this,"Please enter your name!",Toast.LENGTH_SHORT).show();
            return;
        }
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity == 100){
             Toast.makeText(this,"more than 100 cups can't be made",Toast.LENGTH_SHORT).show();
             return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity <= 1){
            Toast.makeText(this,"Please order at least one cup",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}
