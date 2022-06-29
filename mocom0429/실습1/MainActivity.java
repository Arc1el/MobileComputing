package com.example.myapp14;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    EditText ed;
    ImageView imageView;
    private final int MY_PERMISSION_REQUEST_STORAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        ed = (EditText) findViewById(R.id.ed);

        checkPermission();
    }

    public void clearText(View v){
        ed.setText("");
    }

    public void shared_save(View v){
        SharedPreferences settings = getSharedPreferences("testShared", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", ed.getText().toString());
        editor.commit();
    }

    public void shared_load(View v){
        SharedPreferences settings = getSharedPreferences("testShared", MODE_PRIVATE);
        String str = settings.getString("name", "");
        ed.setText(str);
    }

    public void internal_save_click(View v){
        try {
            FileOutputStream outFs = openFileOutput("internal.txt", MODE_PRIVATE);
            String str = ed.getText().toString();
            outFs.write(str.getBytes());
            outFs.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void internal_load_click(View v){
        try {
            FileInputStream inFs = openFileInput("internal.txt");

            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            ed.setText(new String(txt));

        } catch (IOException e){

        }
    }

    public void external_save_click(View v){
        File myDir = new File(getExternalFilesDir(null).getAbsolutePath() + "/mydir");
        myDir.mkdir();
        Log.d("hyeon", myDir.toString());

        try {
            FileOutputStream outFs = new FileOutputStream(new File(myDir, "external.txt"));
            String str = ed.getText().toString();
            outFs.write(str.getBytes());
            outFs.close();
            Log.d("hyeon", "external save ok");
        }catch (IOException e){
            Log.d("hyeon", "external save error" + e.toString());
        }
    }

    public void external_load_click(View v) {
        try {
            File myDir = new File(getExternalFilesDir(null).getAbsolutePath() + "/mydir");
            FileInputStream inFs = new FileInputStream(new File(myDir, "external.txt"));
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            ed.setText(new String(txt));
        } catch (IOException e) {
            Log.d("hyeon", "external load error" + e.toString());
        }
    }

    public void file_copy(View v){
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final String strSDpath2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

        Log.d("hyeon", strSDpath + "public path = " + strSDpath2);

        try {
            InputStream inFs = getResources().openRawResource(R.raw.img1);

            FileOutputStream outFs = new FileOutputStream(new File(strSDpath, "img1cpy.png"));
            FileOutputStream outFs2 = new FileOutputStream(new File(strSDpath2, "img1cpy.png"));
            int c;

            while((c = inFs.read()) != -1){
                outFs.write(c);
                outFs2.write(c);
            }

            outFs.close();
            outFs2.close();
            inFs.close();
        }catch (IOException e){
            Log.d("hyeon", "error" + e.toString());
        }
    }

    private void checkPermission() {

        //Log.d("hwang","check=" + checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSION_REQUEST_STORAGE);
            // MY_PERMISSION_REQUEST_STORAGE is an
            // app-defined int constant

        } else {
            //Log.e(TAG, "permission deny");
            //writeFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_STORAGE:

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to write the permission.
                    Toast.makeText(this, "Read/Write external storage 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                }

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted,
                    Toast.makeText(this, "권한 승인", Toast.LENGTH_SHORT).show();
                    Log.d("hyeon", "Permission granted");

                } else {
                    Log.d("hyeon", "Permission deny");
                    // permission denied,
                }
                break;
        }
    }

    public void delete_click(View v){
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(strSDpath, "img1cpy.png");
        file.delete();
    }

    public void show_image_click(View v){
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(strSDpath, "img1cpy.png");

        imageView.setImageURI(Uri.fromFile(file));
    }
}