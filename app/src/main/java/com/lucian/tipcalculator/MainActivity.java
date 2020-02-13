package com.lucian.tipcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // Attributes
    private EditText editTextCheckValue;
    private TextView textViewPercentage, textViewTipValue, textViewTotalValue;
    private SeekBar seekBarTip;
    private int percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCheckValue = (EditText) findViewById(R.id.editTextCheckValue);
        textViewPercentage = (TextView) findViewById(R.id.textViewPercentage);
        textViewTipValue = (TextView) findViewById(R.id.textViewTipValue);
        textViewTotalValue = (TextView) findViewById(R.id.textViewTotalValue);
        seekBarTip = (SeekBar) findViewById(R.id.seekBarTip);

        // Seekbar Control
        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentage = seekBar.getProgress();
                textViewPercentage.setText(percentage + "%");
                if(inputFieldValidation(editTextCheckValue.getText().toString())){

                }
                else {
                    calculations();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(inputFieldValidation(editTextCheckValue.getText().toString())){
                    errorMessage();

                }

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(inputFieldValidation(editTextCheckValue.getText().toString())){

                }

            }
        });

    }

    public void calculations(){
        // Recovering check value
        double checkValue = Double.parseDouble(editTextCheckValue.getText().toString());

        // Calculating tip value
        double tipValue = (checkValue * percentage) / 100;

        // Calculating Total
        double totalValue = checkValue + tipValue;

        // Rounding the variables for a better display
        tipValue = Math.round(tipValue * 100.0) / 100.0;
        totalValue = Math.round(totalValue * 100.0) / 100.0;

        // Showing Tip and total on screen
        textViewTipValue.setText("$ " + tipValue);
        textViewTotalValue.setText("$ " + totalValue);

    }

    public boolean inputFieldValidation(String value){
        if(value == null || value.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    public void errorMessage(){
        // Creating AlertDialog Object
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        // Configuring Title and Message
        dialog.setTitle("Error");
        dialog.setMessage("Please enter the value of the check.");

        // Configuring if the AlertDialog can cancelled or not
        dialog.setCancelable(false);

        // Configure Icon
        dialog.setIcon(android.R.drawable.ic_delete);

        // Configuring actions for the 'Yes' and 'No' buttons
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                seekBarTip.setProgress(0);
                percentage = 0;

            }
        });

        // Create and show the AlertDialog
        dialog.create();
        dialog.show();
    }
}
