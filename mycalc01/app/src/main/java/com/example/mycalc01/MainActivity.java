package com.example.mycalc01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView result;
    Button btn_op, btn_cl, btn_del, btn_p, btn_m,
            btn_n1, btn_n2, btn_n3, btn_n4, btn_n5,
            btn_n6, btn_n7, btn_n8, btn_n9, btn_n0,
            btn_mul, btn_div, btn_result;

    String str;

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

        result = (TextView) findViewById(R.id.result);
        btn_n1 = (Button) findViewById(R.id.btn_num1);
        btn_n2 = (Button) findViewById(R.id.btn_num2);

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
        String new_string = now_string.substring(0, now_string.length()-1);
        result.setText((new_string));
    }

    public void btn_result(View view){
        result = (TextView) findViewById(R.id.result);
        String exp1 = result.getText().toString();

        Expression exp2 = new Expression(exp1);

        String exp_final = String.valueOf(exp2.calculate());
        result.setText(exp_final);

    }
}