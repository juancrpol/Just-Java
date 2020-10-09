package com.example.justjava;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckbox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();
        EditText nameField = findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping.
     * @param addChocolate    is whether or not the user wants chocolate topping.
     * @return total price.
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream) {
            basePrice += 1;
        }

        if (addChocolate) {
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    /**
     * Method that creates a order summary.
     *
     * @param name            the name of person.
     * @param priceOrder      The total price.
     * @param addWhippedCream is whether or not the user wants whipped cream topping.
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @return summary of order.
     */
    private String createOrderSummary(String name, int priceOrder, boolean addWhippedCream, boolean addChocolate) {
        String messageOrder = "Name: " + name;
        messageOrder += "\nAdd whipped cream? " + addWhippedCream;
        messageOrder += "\nAdd chocolate? " + addChocolate;
        messageOrder += "\nQuantity: " + quantity;
        messageOrder += "\nTotal: $" + priceOrder;
        messageOrder += "\nThank you!";
        return messageOrder;
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param number quantity of coffees.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     *
     * @param message to display in order.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast toast = Toast.makeText(this, "You cann't have more than 100 coffees.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast toast = Toast.makeText(this, "You cann't have less than 1 coffee.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }
}