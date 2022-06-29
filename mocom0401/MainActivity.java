package com.example.myapp04;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout baseLayout = new LinearLayout((this));
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setBackgroundColor(Color.rgb(0,255,0));
        setContentView(baseLayout, params);

        Button btn = new Button(this);
        btn.setText("this is button");
        btn.setBackgroundColor(Color.MAGENTA);
        //btn.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        baseLayout.addView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "코드로 생성한 버튼",Toast.LENGTH_SHORT).show();
            }
        });

        TextView tv = new TextView(this);
        tv.setText("this section is tv");
        tv.setBackgroundColor(Color.WHITE);
        baseLayout.addView(tv);


    }
}