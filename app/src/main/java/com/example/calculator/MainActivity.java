package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MaterialButton btnC, btnbracket1, btnbracket2, btndivide, btn0,btn7,btn8,btn9,btn4,btn5,btn6,btn1,btn2,btn3;
    MaterialButton btnAC, btnX, btnadd, btnsub, btndot, btnequals;
    TextView resultView, solutionView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.result);
        solutionView = findViewById(R.id.solutionView);

        assignedIds();
        btnListen();


    }

    void assignedIds(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnAC = findViewById(R.id.btnAC);
        btnbracket1 = findViewById(R.id.btnbracket);
        btnbracket2 = findViewById(R.id.btnbracket2);
        btndivide = findViewById(R.id.btndivide);
        btnX = findViewById(R.id.btnmultiply);
        btnadd = findViewById(R.id.btnadd);
        btnequals = findViewById(R.id.btnequals);
        btnsub = findViewById(R.id.btnsub);
        btndot = findViewById(R.id.btndot);
        btnC = findViewById(R.id.btnC);
    }
    void btnListen(){
        listener(btn0);
        listener(btn1);
        listener(btn2);
        listener(btn3);
        listener(btn4);
        listener(btn5);
        listener(btn6);
        listener(btn7);
        listener(btn8);
        listener(btn9);
        listener(btn0);
        listener(btnAC);
        listener(btnbracket1);
        listener(btnbracket2);
        listener(btnadd);
        listener(btndivide);
        listener(btnX);
        listener(btndot);
        listener(btnsub);
        listener(btnequals);
        listener(btnC);


    }

    void listener(MaterialButton button){
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionView.getText().toString();

        //When clicked on AC clearing numbers
        if(buttonText.equals("AC")){
            solutionView.setText(" ");
            resultView.setText("0");
            return;
        }

        //When = gives the solution
        if (buttonText.equals("=")){
            solutionView.setText(resultView.getText());
        }
        if (buttonText.equals("C")){
            dataToCalculate =dataToCalculate.substring(0,
                    dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionView.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultView.setText(finalResult);
        }

    }

    String getResult(String data){

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
           String finalResult=  context.evaluateString(scriptable, data, "Javascript",
                    1, null).toString();
           if(finalResult.endsWith(" .0")){
               finalResult = finalResult.replace(".0", "");
           }

           return finalResult;
        }
        catch( Exception e){
            return "Err";
        }
    }
}