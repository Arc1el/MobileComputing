package com.example.myapp20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView tv_title, tv_desc, tv_price;
    ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_price = (TextView) findViewById(R.id.tv_price);
        iv = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        if (intent != null){
            tv_title.setText(intent.getStringExtra("title"));
            tv_desc.setText(intent.getStringExtra("desc"));
            tv_price.setText(intent.getStringExtra("price"));
            iv.setImageResource(intent.getIntExtra("img_src", 0));
        }
    }
    public void setBtn_finish(View v){
        finish();
    }
}
