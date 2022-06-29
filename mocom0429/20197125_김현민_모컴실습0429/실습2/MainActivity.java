package com.example.myapp15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    EditText ed;
    Button btn_write;
    String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dp = (DatePicker) findViewById(R.id.datePicker);
        ed = (EditText) findViewById(R.id.editText);
        btn_write = (Button) findViewById(R.id.write_btn);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                file_name = Integer.toString(i) + "_" +
                        Integer.toString(i1 + 1) + "_" +
                        Integer.toString(i2) + ".txt";
                String str = read_diary(file_name);
                ed.setText(str);
                btn_write.setEnabled(true);
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream outFs = openFileOutput(file_name, Context.MODE_PRIVATE);
                    String str = ed.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), file_name + " 이 저장됨", Toast.LENGTH_SHORT).show();

                }catch (IOException e){
                }
            }
        });
    }
    String read_diary(String f_name) {
        String diary_str = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(f_name);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diary_str = (new String(txt)).trim();
            btn_write.setText("수정하기");

        }catch (IOException e){
            ed.setHint("일기 없음");
            btn_write.setText("새로 저장");
        }
        return diary_str;
    }
}