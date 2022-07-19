package com.example.calc02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void func_calc(View view){
        EditText et1 = (EditText) findViewById(R.id.edit_txt1);
        String exp = et1.getText().toString();
        Expression exp2 = new Expression(exp);
        String exp_final = String.valueOf(exp2.calculate());

        if(exp_final == "NaN")
            et1.setText(exp + " = " + "ERROR");
        else
            et1.setText(exp + " = " + exp_final);
    }
};