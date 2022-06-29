package com.example.homework04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ComputableLiveData;

import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox check_twice, check_bts;
    RadioGroup radio_group_mem, radio_group_scale_type;
    RadioButton radio_btn_mem1, radio_btn_mem2, radio_btn_mem3;
    ImageView imgview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check_twice = (CheckBox) findViewById(R.id.check_twice);
        check_bts = (CheckBox) findViewById(R.id.check_bts);
        check_twice.setOnCheckedChangeListener(myCheckListener);
        check_bts.setOnCheckedChangeListener(myCheckListener);
        imgview1 = (ImageView) findViewById((R.id.imageView));
        radio_group_scale_type = (RadioGroup) findViewById(R.id.radiogroup_scale);

        radio_group_scale_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radio_fitcenter){
                    imgview1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
                else if(i == R.id.radio_matrix){
                    imgview1.setScaleType(ImageView.ScaleType.MATRIX);
                    Matrix matrix = imgview1.getImageMatrix();
                    float scale = 0.5f;
                    matrix.setScale(scale, scale);
                    imgview1.setImageMatrix(matrix);
                }
                else if(i == R.id.radio_fitxy){
                    imgview1.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                else {
                    imgview1.setScaleType(ImageView.ScaleType.CENTER);
                }
            }
        });
    }

    CompoundButton.OnCheckedChangeListener myCheckListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            radio_btn_mem1 = (RadioButton) findViewById(R.id.radio_mem1);
            radio_btn_mem2 = (RadioButton) findViewById(R.id.radio_mem2);
            radio_btn_mem3 = (RadioButton) findViewById(R.id.radio_mem3);
            imgview1 = (ImageView) findViewById(R.id.imageView);
            radio_group_mem = (RadioGroup) findViewById((R.id.radiogroup_mem));

            if(compoundButton.getId() == R.id.check_twice) {
                radio_btn_mem1.setText("채영");
                radio_btn_mem2.setText("사나");
                radio_btn_mem3.setText("나연");

                radio_group_mem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        StringBuilder mStr = new StringBuilder();

                        if(i == R.id.radio_mem1)
                            imgview1.setImageResource(R.drawable.cy);
                        else if(i == R.id.radio_mem2)
                            imgview1.setImageResource(R.drawable.sn);
                        else
                            imgview1.setImageResource(R.drawable.ny);
                    }
                });

            }
            else {
                radio_btn_mem1.setText("정국");
                radio_btn_mem2.setText("태형");
                radio_btn_mem3.setText("지민");

                radio_group_mem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        StringBuilder mStr = new StringBuilder();

                        if(i == R.id.radio_mem1)
                            imgview1.setImageResource(R.drawable.jg);
                        else if(i == R.id.radio_mem2)
                            imgview1.setImageResource(R.drawable.th);
                        else
                            imgview1.setImageResource(R.drawable.jm);
                    }
                });
            }
        }
    };
}