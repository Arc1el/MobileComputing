package com.example.myapp15;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
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
    Switch lock_switch;
    Integer lockon_flag;
    Integer lockoff_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dp = (DatePicker) findViewById(R.id.datePicker);
        ed = (EditText) findViewById(R.id.editText);
        btn_write = (Button) findViewById(R.id.write_btn);
        lock_switch = (Switch) findViewById(R.id.switch1);

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
                if(!lock_switch.isChecked())
                    ed.setText(str);
                else
                    ed.setText("Please Lock Off");
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
        //shared preference 검사하여 기존값이 on off 인지를 파악
        if (shared_check()){
            Log.d("hyeon", "inside true");
            lock_switch.setChecked(true);
            ed.setText("Please Lock Off");
            btn_write.setText("먼저 잠금을 해제하세요");
        }
        else {
            Log.d("hyeon", "inside false");
            lock_switch.setChecked(false);
            ed.setText("Please Lock On");
        }
        lockon_flag = 0;
        lockoff_flag = 0;

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        lock_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (lock_switch.isChecked()){
                    if(lockoff_flag != 1) {
                        Toast.makeText(MainActivity.this, "checked", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("shared_preference", MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("checked", "true");
                        editor.commit();

                        dlg.setTitle("암호를 변경하시겠습니까?");
                        EditText ed = new EditText(getApplicationContext());
                        dlg.setView(ed);
                        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lockon_flag = 0;
                                SharedPreferences settings = getSharedPreferences("shared_preference", MODE_PRIVATE);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("password", ed.getText().toString());
                                editor.commit();
                            }

                        });
                        dlg.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lockon_flag = 1;
                                lock_switch.setChecked(false);
                            }
                        });
                        dlg.show();
                    }
                }
                else{
                    if(lockon_flag != 1) {
                        Toast.makeText(MainActivity.this, "unchecked", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("shared_preference", MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("checked", "");
                        editor.commit();

                        dlg.setTitle("패스워드 입력");
                        EditText ed = new EditText(getApplicationContext());
                        dlg.setView(ed);
                        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lockoff_flag = 0;
                                SharedPreferences settings = getSharedPreferences("shared_preference", MODE_PRIVATE);
                                if (settings.getString("password", "") == ed.getText().toString()) {
                                    lock_switch.setChecked(false);
                                }
                            }
                        });
                        dlg.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lockoff_flag = 1;
                                lock_switch.setChecked(true);
                            }
                        });
                        dlg.show();
                    }
                }
            }
        });
    }
    public boolean shared_check(){
        //shared preference 검사하여 기존값이 on off 인지를 파악
        SharedPreferences settings = getSharedPreferences("shared_preference", MODE_PRIVATE);
        String str = settings.getString("checked","");
        Log.d("hyeon","str = " + str);

        if(str == "") return false;
        else return true;
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
        if (shared_check()){
            btn_write.setText("먼저 잠금을 해제하세요");
        }
        return diary_str;
    }
}