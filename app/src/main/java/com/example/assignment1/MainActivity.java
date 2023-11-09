package com.example.assignment1;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Calculator cals = new Calculator();
    TextView text;
    TextView histText;
    Button btnone;
    Button btntwo;
    Button btnthree;
    Button btnfour;
    Button btnfive;
    Button btnsix;
    Button btnseven;
    Button btneight;
    Button btnnine;
    Button btnzero;
    Button btnplus;
    Button btnsub;
    Button btnmul;
    Button btndiv;
    Button btnc;
    Button btnequals;
    Button btnhis;

    String currentText = "";
  String displayHis = "";
    String s;
  ArrayList<String> displayList ;
    ArrayList<String> histList ;
    boolean historyFlag = false;
    String updatedText ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updatedText = ((MyApp)getApplication()).calculatorString ;
        historyFlag = ((MyApp)getApplication()).historyFlag ;
        histList = ((MyApp)getApplication()).histList ;

        text = findViewById(R.id.text);
        histText = findViewById(R.id.text_his);
        btnone = findViewById(R.id.btnone);
        btnone.setOnClickListener(this);
        btntwo = findViewById(R.id.btntwo);
        btntwo.setOnClickListener(this);
        btnthree = findViewById(R.id.btthree);
        btnthree.setOnClickListener(this);
        btnfour = findViewById(R.id.btnfour);
        btnfour.setOnClickListener(this);
        btnfive = findViewById(R.id.btnfive);
        btnfive.setOnClickListener(this);
        btnsix = findViewById(R.id.btnsix);
        btnsix.setOnClickListener(this);
        btnseven = findViewById(R.id.btnseven);
        btnseven.setOnClickListener(this);
        btneight = findViewById(R.id.btneight);
        btneight.setOnClickListener(this);
        btnnine = findViewById(R.id.btnnine);
        btnnine.setOnClickListener(this);
        btnzero = findViewById(R.id.btnzero);
        btnzero.setOnClickListener(this);

        btnplus = findViewById(R.id.btnplus);
        btnplus.setOnClickListener(this);
        btnsub = findViewById(R.id.btnsub);
        btnsub.setOnClickListener(this);
        btnmul = findViewById(R.id.btnmul);
        btnmul.setOnClickListener(this);
        btndiv = findViewById(R.id.btndiv);
        btndiv.setOnClickListener(this);

        btnhis = findViewById(R.id.btnhist);
        btnhis.setOnClickListener(this);

        btnc = findViewById(R.id.btnc);
        btnc.setOnClickListener(this);

        btnequals = findViewById(R.id.btnequals);
        btnequals.setOnClickListener(this);

        text.setText(updatedText);
        String splus = "";
        for (String s1 : histList) {
            System.out.println("s in history " + s1);
            splus += s1+"\n" ;
            displayHis = splus  ;

        }
        histText.setText(displayHis);

    }


    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String btnString = btn.getText().toString();

        currentText = text.getText().toString();
        if(btn != btnc && btn != btnequals && btn != btnhis){

            String updatedString = currentText + btnString ;
            System.out.println("cals.clearPush "+cals.clearPush);
            text.setText(updatedString);
            ((MyApp)getApplication()).calculatorString = updatedString ;
            if(cals.clearPush){
                text.setText(" ");
                cals.calculatorString = cals.calculatorString.replace(cals.calculatorString,"") ;
                cals.clearPush = false;
                text.setText(btnString);
                cals.push(btnString);
            }else{
                cals.push(btnString);
            }

        }
        if(btn == btnc){
            text.setText(" ");
            cals.clearPush = true;
        } else if (btn == btnequals) {

            if (cals.validateFun()){
                cals.clearPush = true;
                String res = String.valueOf(cals.calculate());
                String updatedText = currentText  ;
                text.setText(updatedText + "= "+res);
                ((MyApp)getApplication()).calculatorString = updatedText + "= "+res ;

                if(((MyApp)getApplication()).historyFlag){
                cals.pushHistory(updatedText + "= "+res);
                    ((MyApp)getApplication()).histList.add(updatedText + "= "+res);

                    String splus = "";
                    for (String s1 : ((MyApp)getApplication()).histList) {
                        System.out.println("s in history " + s1);
                        splus += s1+"\n" ;
                        displayHis = splus  ;

                    }
                    histText.setText(displayHis);

                }else{
                    histText.setText(" ");
                }
            }else{
                Toast.makeText(MainActivity.this,"Enter single digit followed by operator",Toast.LENGTH_LONG).show();
            }
        } else if (btn == btnhis) {
            if(!((MyApp)getApplication()).historyFlag){
                ((MyApp)getApplication()).historyFlag = true;
                btnhis.setText(R.string.standard_no_history);
                histText.setText(" ");
            }else{
                ((MyApp)getApplication()).historyFlag = false;
                btnhis.setText(R.string.advance_with_history);
                histText.setText(" ");
            }
        }
    }
}