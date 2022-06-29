package com.example.myapp18;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    RatingBar ratbar1, ratbar2, ratbar3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ratbar1 = (RatingBar) findViewById(R.id.ratbar1);
        ratbar2 = (RatingBar) findViewById(R.id.ratbar2);
        ratbar3 = (RatingBar) findViewById(R.id.ratbar3);


        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle;
            bundle = intent.getBundleExtra("bundle");
            float stars[] = bundle.getFloatArray("tv_value");
            ratbar1.setRating(stars[0]);
            ratbar2.setRating(stars[1]);
            ratbar3.setRating(stars[2]);

        }
    }
    public void btn_main(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();

        float rating[] = {ratbar1.getRating(), ratbar2.getRating(), ratbar3.getRating()};
        Log.d("hyeon", "second activity data"+rating[0]);
        bundle.putFloatArray("tv_value", rating);
        intent.putExtra("bundle", bundle);
        setResult(RESULT_OK, intent);
        finish();

    }
}
