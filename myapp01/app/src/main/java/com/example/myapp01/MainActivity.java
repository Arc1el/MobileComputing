package com.example.myapp01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button3;
    Button button4;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"button1 clicked", Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(this);
        button4.setOnClickListener(mClickListener);
        button5.setOnClickListener(new MyOnClick());
    }
    public void btn2_click(View view){
        Toast.makeText(getApplicationContext(),"button2 clicked", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view){
        Toast.makeText(getApplicationContext(),"button3 clicked", Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener mClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"button4 clicked", Toast.LENGTH_SHORT).show();
        }
    };

    class MyOnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"button5 clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
