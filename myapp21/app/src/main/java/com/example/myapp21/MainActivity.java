package com.example.myapp21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText et_name, et_num, et_name_result, et_num_result;
    Button btn_init, btn_insert, btn_select, btn_mod, btn_del;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = (EditText) findViewById(R.id.et_name);
        et_num = (EditText) findViewById(R.id.et_num_people);
        et_name_result = (EditText) findViewById(R.id.et_name_result);
        et_num_result = (EditText) findViewById(R.id.et_num_result);

        btn_init = (Button) findViewById(R.id.btn_init);
        btn_insert = (Button) findViewById(R.id.btn_input);
        btn_select = (Button) findViewById(R.id.btn_check);
        btn_mod = (Button) findViewById(R.id.btn_mod);
        btn_del = (Button) findViewById(R.id.btn_del);

        myHelper = new myDBHelper(this);

        btn_init.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '"
                        + et_name.getText().toString() + "', "
                        + et_num.getText().toString() + ");");
                sqlDB.close();
            }
        });

        btn_select.setOnClickListener((v) -> {
            sqlDB = myHelper.getReadableDatabase();
            Cursor cursor;
            cursor = sqlDB.rawQuery("SELECT * FROM groupTBL", null);

            String strNames = "그룹이름" + "\r\n" + "--------" + "\r\n";
            String strNumbers = "인원" + "\r\n" + "--------" + "\r\n";

            while (cursor.moveToNext()){
                strNames += cursor.getString(0) + "\r\n";
                strNumbers += cursor.getString(1) + "\r\n";
            }

            et_name_result.setText(strNames);
            et_num_result.setText(strNumbers);

            cursor.close();
            sqlDB.close();
        });

        btn_mod.setOnClickListener((v) -> {
            sqlDB = myHelper.getReadableDatabase();
            sqlDB.execSQL("UPDATE groupTBL SET gNumber = "
                        + et_num.getText().toString()
                        + " WHERE gName = '"
                        + et_name.getText().toString()
                        + "';");
            sqlDB.close();
        });

        btn_del.setOnClickListener((v) -> {
            sqlDB = myHelper.getWritableDatabase();
            sqlDB.execSQL("DELETE FROM groupTBL WHERE gName = '"
                        + et_name.getText().toString()
                        + "';");
            sqlDB.close();
        });
    }


    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context){

            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}