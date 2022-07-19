package com.example.calc01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView result;

    public void mod_result(String add){
        result = (TextView) findViewById(R.id.result);
        String now_string = result.getText().toString();
        String new_string = now_string + add;
        result.setText(new_string);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btn_nums(View view){
        Button this_btn = findViewById(view.getId());
        String btn_str = this_btn.getText().toString();
        mod_result(btn_str);
    }

    public void btn_ops(View view){
        Button this_btn = findViewById(view.getId());
        String btn_str = " " + this_btn.getText().toString() + " ";
        mod_result(btn_str);
    }

    public void btn_del(View view){
        result = (TextView) findViewById(R.id.result);
        String now_string = result.getText().toString();
        if(now_string.length() == 0)
            result.setText((""));
        else
            result.setText(now_string.substring(0, now_string.length() - 1));

    }

    public void btn_result(View view){
        result = (TextView) findViewById(R.id.result);
        String exp1 = result.getText().toString();

        Expression exp2 = new Expression(exp1);

        String exp_final = String.valueOf(exp2.calculate());

        if(exp_final == "NaN")
            result.setText(exp1 + " = " + "ERROR");
        else
            result.setText(exp1 + " = " + exp_final);

    }

    public void btn_clear(View view){
        result = (TextView) findViewById(R.id.result);
        result.setText("");
    }
}