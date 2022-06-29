package com.example.myapp18;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView iv1, iv2, iv3;
    TextView tv1, tv2, tv3;
    float tv1_value=0, tv2_value=0, tv3_value=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

    }
    public void btn_result_click(View view){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();

        float num[] = {tv1_value, tv2_value, tv3_value};
        bundle.putFloatArray("tv_value", num);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, 10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data != null){
            Log.d("hyeon", "ok");
            Bundle bundle = data.getBundleExtra("bundle");
            float num[] = bundle.getFloatArray("tv_value");
            tv1_value = num[0]; tv2_value = num[1]; tv3_value = num[2];
            Log.d("hyeon", ""+tv1_value);
            tv1.setText(""+tv1_value); tv2.setText(""+tv2_value); tv3.setText(""+tv3_value);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void iv_click(View view){
        switch(view.getId()){
            case R.id.iv1:
                tv1_value = Float.parseFloat(tv1.getText().toString());
                tv1_value += 1;
                tv1.setText("" + tv1_value);
                break;
            case R.id.iv2:
                tv2_value = Float.parseFloat(tv2.getText().toString());
                tv2_value += 1;
                tv2.setText("" + tv2_value);
                break;
            case R.id.iv3:
                tv3_value = Float.parseFloat(tv3.getText().toString());
                tv3_value += 1;
                tv3.setText("" + tv3_value);
                break;
        }
    }
}