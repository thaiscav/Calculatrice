package com.example.thais.calculatrice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.widget.TextView;
import java.lang.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_Error = "Error:";
    private TextView displayCalc = null;
    private String operation = "";
    private double total = 0.0 , val1 = 0.0, val2 = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayCalc = (TextView) findViewById(R.id.txtScreen);

    }//fin onCreate

    protected void onClickNumber (View v){

        Button number = (Button) v;

        if (displayCalc.getText().toString().equals("0"))
        {
            displayCalc.setText("");
        }

        displayCalc.append(number.getText());

    }//fin onClickNumber

    public void onClickOperation(View v){

        //Prendre l'operateur
        Button button = (Button) v;
        operation = button.getText().toString();

        getVal1();

    }//fin onClickOperation

    public void onClickSpecialOperation(View v){

        //Prendre l'operateur
        Button button = (Button) v;
        operation = button.getText().toString();

        getVal1();
        calculate();

    }//fin onClickSpecialOperation

    public void getVal1(){

        if ((displayCalc.getText().length() == 0) || (displayCalc.getText().equals("0")))
        {
            return;
        }

        val1 = Double.parseDouble(displayCalc.getText().toString());
        displayCalc.setHint(displayCalc.getText().toString());
        displayCalc.setText("");
    }//fin getVal1

    public void onClickDot(View v){

        if (!displayCalc.getText().toString().contains(".")) {

            if (displayCalc.getText().length() == 0)
            {
                displayCalc.append("0");
            }

            displayCalc.append(".");
        }

    }//fin onClickDot

    public void onClickEqual(View v){

        if ((displayCalc.getText().length() == 0) || (displayCalc.getText().equals("0")))
        {
            return;
        }

        val2 = Double.parseDouble(displayCalc.getText().toString());

        calculate();

    }//fin onClickEqual

    private void calculate() {

              switch (operation) {

                case "+": // Add
                    total = val1 + val2;
                    break;

                case "-": // subs
                    total = val1 - val2;
                    break;

                case "*": // mult
                    total = (val1 * val2);
                    break;

                case "/": // div
                    total = (val1 / val2);
                    break;

                  case "%": // percentage
                      total = (val1/100);
                      break;

                  case "1/x": // 1/x
                      total = (1 / val1);
                      break;

                  case "√": // square root
                      total = Math.sqrt(val1);
                      break;

                default:
                    Toast.makeText(getApplicationContext(), "Impossible de calculer", Toast.LENGTH_SHORT).show();
                    break;

        }//fin switch

        displayCalc.setHint(String.valueOf(total));
        displayCalc.setText("");
        val1 = total;

    }//fin calculate

    public void onClickClear(View v) {

        clearDisplay();

    }//fin onClickClear

    public void clearDisplay(){

        displayCalc.setText("0");
        displayCalc.setHint("0");

        total = 0.0;
        val1 = 0.0;
        val2 = 0.0;
        operation = "";

    }//fin clearDisplay

    public void onClickSign(View v){//Changer sign + et -

        if (!displayCalc.getText().toString().equals("0"))
        {
            float inversion;

            inversion = -(Float.parseFloat(displayCalc.getText().toString()));

            displayCalc.setText(String.valueOf(inversion));
        }

    }//fin onClickSign

    public void onClickDel(View v){

        del();

    }//fin onClickDel

    public void del() {

        if (displayCalc.length() > 0)// && strCalc.charAt(strCalc.length()-1)=='x')
        {
            String val = displayCalc.getText().toString();
            String newVal = val.substring(0, val.length()-1);


            if(newVal.length() != 0) {
                displayCalc.setText(newVal);
            }
            else {
                displayCalc.setText("0");
            }
        }
    }//fin del

}//fin MainActivity
