package com.example.thais.calculatrice;

import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.widget.*;
import android.view.*;
import android.widget.TextView;
import android.util.Log;
import java.util.regex.Pattern;
import java.lang.Double;
import android.text.TextWatcher;


public class MainActivity extends AppCompatActivity {

    private static final String TAG_Error = "Error:";
    private TextView screen, screen2;    //txtScreen e txtScreen2 = mostra o resultado e calc
    private String displayCalc = "", displayResult = "", valueEntered = "", operator = null, memoOperator = null, sign = "+";

    //teste
    private Double result = null, val = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen2 = (TextView) findViewById(R.id.txtScreen2);
        screen2.setText(displayCalc);

        screen = (TextView) findViewById(R.id.txtScreen);
        screen.setText(displayCalc);

    }//fin onCreate

    private void updateScreen(){

        screen2.setText(displayCalc); // = calcul

        if (result != null) {

            displayResult = result.toString();

        }

        screen.setText(displayResult); // = result

    }// fin updateScreen

    protected void onClickNumber (View v){

        Button b = (Button) v;

        if (displayCalc == "0")
        {
            displayCalc="";
        }

        //TXT: Sauvagarde chaque valeur tappé
        valueEntered += b.getText().toString();
        Log.e("valueEntered", ": " + valueEntered);

        //TXT: Sauvagarde / concatene les valeurs et les operateurs
        displayCalc += b.getText().toString();
        Log.e("displayCalc", ": " + displayCalc);

        //DOUBLE: Prendre chaque valeur tappé
        try{
            val = Double.parseDouble(valueEntered);
            Log.e("value", ": " + val);
        }
        catch(NumberFormatException e){
            Log.d(TAG_Error, e.getMessage());
        }

        if (operator != null)
        {
            calculate();
        }

        updateScreen();

    }//fin onClickNumber

    private void save(){

        //resultorise le 1 valeur
        result = val;
        // Efface la variable afin de prendre le 2 valeur
        val = null;

        Log.e("result2", ": " + result);
        Log.e("value", ": " + val);

    }

    public void onClickOperation(View v){

        //Prendre l'operateur
        Button b = (Button) v;
        operator = b.getText().toString();

        if (operator != memoOperator){

            //Display tout le contenu concatenné
            displayCalc += operator;

            //Efface le display afin de prendre le 2 txt
            valueEntered = "";

            //memoriser operator
            memoOperator = operator;

            updateScreen();
            save();
        }

    }//fin onClickSigns

    private void calculate() { //String a, String b, String op

        switch (operator) {

            case "+": // Add
                //result = result + result;
                result += val;
                break;
                //(Double.valueOf(a) + Double.valueOf(b));

            case "-": // subs
                //result = result - result;
                result -= val;
                break;
                //(Double.valueOf(result) - Double.valueOf(val));

            case "*": // mult
                result *= (val);
                break;
                //(Double.valueOf(result) * Double.valueOf(val));

            case "/": // div
                result /= (val);
                break;
                //(Double.valueOf(result) / Double.valueOf(val));

            case "%": // percentage
                result = (Double.valueOf(result) * (Double.valueOf(val)/100));
                break;

            case "x": // 1/x
                result = 1 / (Double.valueOf(result));
                break;

            case "2": // square root
                result = Double.valueOf(result);
                break;

            default: result = null;
                    // displayResult = "erreur";
        }//fin switch

        memoOperator = null;

    }//fin calculate

    public void onClickEqual(View v){

/*
        String[] operation = displayCalc.split(Pattern.quote(operator));

        if (operation.length < 2) return;

        Double result = calculate(operation[0], operation[1],  operator);

        screen.setText(displayCalc + "\n" + String.ValueOf(result));
*/
    }//fin Equal

    public void onClickClear(View v)    {

        result = null;
        val=null;

        operator = "";
        displayCalc = "0";
        displayResult = "";
        valueEntered = "";

        Log.d("Clear - ","result: "+result);
        Log.d("Clear - ","operator: "+operator);

        updateScreen();

    }//fin clear

    public void clear(){

    }

    public void onClickSign(View v){

        //trocar sinal de + para - e de - para +

    }//fin operator + or -

    public void onClickDot(View v){

        displayCalc += ".";

    }//fin Dot

    public void onClickDel(View v){

        del();

    }//fin Del

    public void del() {

        if (displayCalc != null && displayCalc.length() > 0)// && displayCalc.charAt(displayCalc.length()-1)=='x')
        {
            valueEntered = valueEntered.substring(0, valueEntered.length()-1);
            displayCalc = displayCalc.substring(0, displayCalc.length()-1);
            val = Double.parseDouble(valueEntered);

            //calculate();
            updateScreen();

            Log.e("Del valueEntered", ": " + valueEntered);
            Log.e("Del displayCalc", ": " + displayCalc);
            Log.e("Del val", ": " + val);
            Log.e("Del result", ": " + result);
        }
    }


}//fin MainActivity
