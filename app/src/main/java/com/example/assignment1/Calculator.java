package com.example.assignment1;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator extends Application{

    String calculatorString = "";
    boolean validated = false;
    boolean clearPush = false;
    ArrayList<String> histList = new ArrayList<>();
    int res = 0;
    void push(String s) {
        if (!clearPush) {
            calculatorString += s;
            System.out.println("s in push method " + calculatorString);
        }else {
            System.out.println("s in push method else " + calculatorString);
        }
    }
    void pushHistory(String h) {
        histList.add(h);
    }

    boolean validateFun(){
        Log.d("msg", "in validatefun");
        String[] strArr = calculatorString.split("((?<=\\+)|(?=\\+)|(?<=-)|(?=-)|(?<=\\*)|(?=\\*)|(?<=/)|(?=/))");
        for(int i = 0; i<strArr.length-1; i+=2) {
            System.out.println("strArr "+strArr[i+1]);
            if (Objects.equals(strArr[i + 1], "+") || Objects.equals(strArr[i + 1], "-") ||
                    Objects.equals(strArr[i + 1], "*") || Objects.equals(strArr[i + 1], "/")){
               System.out.println("inside true");
                validated = true;
            }else {
                System.out.println("inside false");
                validated = false;
            }
        }
        return validated;
    }
    int calculate(){
        Log.d("s", "s = "+calculatorString); // 4 + 6 * 9
        int leftOprn = 0;
        int rightOprn = 0;
        String[] strArr = calculatorString.split("((?<=\\+)|(?=\\+)|(?<=-)|(?=-)|(?<=\\*)|(?=\\*)|(?<=/)|(?=/))");
        String[] equationArray = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            equationArray[i] = strArr[i];
            System.out.println("equationArray"+equationArray[i]);
        }

        for(int i = 1; i <= equationArray.length -1; i++){
            System.out.println("i = "+i);
            leftOprn = Integer.parseInt(String.valueOf(equationArray[i - 1]));
            rightOprn = Integer.parseInt(String.valueOf(equationArray[i + 1]));
           System.out.println("leftOprn = "+leftOprn+" rightOprn "+rightOprn);
            switch (equationArray[i]){
                case"+":
                    res = leftOprn + rightOprn;
                    break;
                case "-":
                    res = leftOprn - rightOprn;
                    break;
                case "*":
                    res = leftOprn * rightOprn;
                    break;
                case "/":
                    res = leftOprn / rightOprn;
                    break;
            }

             i +=1;
            equationArray[i] = String.valueOf(res);
            System.out.println("res = "+res);
            System.out.println("at the equationArray[i] = "+i +" res "+equationArray[i]);
        }
            Log.d("res", "res = "+res);
        return res ;

    }




}
