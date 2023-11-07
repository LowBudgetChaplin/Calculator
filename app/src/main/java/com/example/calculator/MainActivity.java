package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result;
    TextView calculation;
    MaterialButton buttonC, openBracketButton, closeBracketButton;
    MaterialButton divideButton, multiplyButton, plusButton, minusButton, buttonEqual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        calculation = findViewById(R.id.calculation);

        assignId(buttonC, R.id.buttonC);
        assignId(openBracketButton, R.id.openBracketButton);
        assignId(closeBracketButton, R.id.closeBracketButton);
        assignId(divideButton, R.id.divideButton);
        assignId(multiplyButton, R.id.multiplyButton);
        assignId(plusButton, R.id.plusButton);
        assignId(minusButton, R.id.minusButton);
        assignId(buttonEqual, R.id.buttonEqual);
        assignId(button0, R.id.button0);
        assignId(button1, R.id.button1);
        assignId(button2, R.id.button2);
        assignId(button3, R.id.button3);
        assignId(button4, R.id.button4);
        assignId(button5, R.id.button5);
        assignId(button6, R.id.button6);
        assignId(button7, R.id.button7);
        assignId(button8, R.id.button8);
        assignId(button9, R.id.button9);
        assignId(buttonAC, R.id.buttonAC);
        assignId(buttonDot, R.id.buttonDot);
    }

    void assignId(MaterialButton button, int id){
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String textBtn = button.getText().toString();
        String nbToCalculate = result.getText().toString();

        if(textBtn.equals("AC")){
            calculation.setText("");
            result.setText("");
            return;
        }
        if(textBtn.equals("=")){
            calculation.setText(result.getText());
        }
        if(textBtn.equals("C")){
            nbToCalculate = nbToCalculate.substring(0, nbToCalculate.length()-1);
        }else{
            nbToCalculate = nbToCalculate + textBtn;
        }


//        nbToCalculate = nbToCalculate + textBtn;
        result.setText(nbToCalculate);
        String finalResult = getResult(nbToCalculate);
        if(!finalResult.equals("Error!")){
            result.setText(finalResult);
        }

//        calculation.setText(textBtn);
    }

    String getResult(String nb){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, nb, "Javascript", 1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Error!";
        }
    }
}