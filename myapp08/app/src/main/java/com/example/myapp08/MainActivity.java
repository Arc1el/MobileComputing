package com.example.myapp08;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    RatingBar ratingBar;
    int counter = 0;
    String img_str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

        ImageView image_view3 = new ImageView(this);
        image_view3.setImageResource(R.drawable.c);
        viewFlipper.addView(image_view3);

        ImageView image_view4 = new ImageView(this);
        image_view4.setImageResource(R.drawable.d);
        viewFlipper.addView(image_view4);

        ratingBar = (RatingBar) findViewById(R.id.rating_bar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int star_rate = (int)(v/0.5)*10;
                if(counter == 0)
                    img_str = "Apple";
                else if(counter == 1)
                    img_str = "Bus";
                else if(counter == 2)
                    img_str = "Carpet";
                else
                    img_str = "Dog";

                Toast.makeText(getApplicationContext(),img_str + " : "
                        + star_rate, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void next_click(View v){
        viewFlipper.showNext();
        if(counter == 3)
            counter = 0;
        else
            counter ++;
        ratingBar.setRating(0);
    }

    public void prev_click(View v){
        if(counter == 0)
            counter = 3;
        else
            counter --;
        viewFlipper.showPrevious();
        ratingBar.setRating(0);
    }
}