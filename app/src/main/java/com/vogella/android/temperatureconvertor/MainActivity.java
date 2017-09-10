package com.vogella.android.temperatureconvertor;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double inp;
    private boolean toCelsius = false;

    public void showToastMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * on each click -
     * a)read the radio button values
     * b)check for null/invalid inputs
     * c)update result box
     */
    public void configureConvertButton(){
        Button convert = (Button) findViewById(R.id.convert);
        final EditText input = (EditText) findViewById(R.id.Input);
        final TextView res = (TextView) findViewById(R.id.result);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RadioButton celsius= (RadioButton) findViewById(R.id.celsius);
                final RadioButton farenheit = (RadioButton) findViewById(R.id.farenheit);
                if(!celsius.isChecked() && !farenheit.isChecked()){
                    showToastMsg("Choose a conversion mechanism");
                    return;
                }
                toCelsius = (celsius.isChecked());
                res.setText("");
                try
                {
                    inp = Double.parseDouble(input.getText().toString());
                    double result = convertValue();
                    res.setText(result + " is the result.");
                }
                catch (Exception e){
                    showToastMsg("Enter a decimal value");
                }

            }
        });
    }


    public double convertValue(){
        if(toCelsius)
            return ((inp - 32) * 5 / 9);
        else
            return ((inp * 9) / 5) + 32;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureConvertButton();

    }
}
