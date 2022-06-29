package com.example.app03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox check_1, check_2;
    RadioGroup rgroup_1;
    RadioButton radio_1, radio_2;
    ImageView imgview_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check_1 = (CheckBox) findViewById(R.id.check_1);
        check_2 = (CheckBox) findViewById(R.id.check_2);

        check_1.setOnCheckedChangeListener(mCheckListener);
        check_2.setOnCheckedChangeListener(mCheckListener);

        imgview_1 = (ImageView) findViewById(R.id.imageView_1);
        imgview_1.setImageResource(R.drawable.v);
        imgview_1.setScaleType(ImageView.ScaleType.MATRIX);

        Matrix matrix = imgview_1.getImageMatrix();
        float scale = 0.2f;
        matrix.setScale(scale, scale);
        imgview_1.setImageMatrix(matrix);

        rgroup_1 = (RadioGroup) findViewById(R.id.rgroup_1);

        rgroup_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                StringBuilder mStr = new StringBuilder();

                if(i == R.id.radio_1){
                    mStr.append("MAN selected");
                    imgview_1.setImageResource(R.drawable.v);
                }
                else{
                    mStr.append("WOMAN selected");
                    imgview_1.setImageResource(R.drawable.bibi);
                }

                Toast.makeText(getApplicationContext(), mStr, Toast.LENGTH_SHORT).show();
            }
        });
    }

    CompoundButton.OnCheckedChangeListener mCheckListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            StringBuilder mStr = new StringBuilder();

            if(compoundButton.getId() == R.id.check_1)
                mStr.append(check_1.getText().toString());
            else
                mStr.append(check_2.getText().toString());

            if(b)
                mStr.append(" " + "chekced");
            else
                mStr.append(" " + "unchecked");

            Toast.makeText(getApplicationContext(), mStr, Toast.LENGTH_SHORT).show();
        }
    };
}