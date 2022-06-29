package com.example.myapp16;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        img_copy();
        imageView = (ImageView) findViewById(R.id.imageView);
        counter = 0;
        change_imageview_resource("apple.jpg");

    }
    public void prev_func(View v){
        if (counter == -1){
            counter = 4;
        }
        switch (counter){
            case 0 : change_imageview_resource("apple.jpg");
                break;
            case 1 : change_imageview_resource("bus.jpg");
                break;
            case 2 : change_imageview_resource("cat.jpg");
                break;
            case 3 : change_imageview_resource("dog.jpg");
                break;
            case 4 : change_imageview_resource("eagle.jpg");
                break;
        }
        counter --;


    }
    public void next_func(View v){
        if (counter == 5){
            counter = 0;
        }
        switch (counter){
            case 0 : change_imageview_resource("apple.jpg");
                break;
            case 1 : change_imageview_resource("bus.jpg");
                break;
            case 2 : change_imageview_resource("cat.jpg");
                break;
            case 3 : change_imageview_resource("dog.jpg");
                break;
            case 4 : change_imageview_resource("eagle.jpg");
                break;
        }
        counter ++;
    }

    public void change_imageview_resource(String filename){
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(strSDpath, filename);

        imageView.setImageURI(Uri.fromFile(file));
    }

    public void img_copy(){
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();

        Log.d("hyeon", strSDpath);
        try {
            InputStream inFs1 = getResources().openRawResource(R.raw.apple);
            InputStream inFs2 = getResources().openRawResource(R.raw.bus);
            InputStream inFs3 = getResources().openRawResource(R.raw.cat);
            InputStream inFs4 = getResources().openRawResource(R.raw.dog);
            InputStream inFs5 = getResources().openRawResource(R.raw.eagle);
            int c;
            FileOutputStream outFs1 = new FileOutputStream(new File(strSDpath, "apple.jpg"));
            while((c = inFs1.read()) != -1){
                outFs1.write(c);
            }
            outFs1.close();
            inFs1.close();
            c = 0;
            FileOutputStream outFs2 = new FileOutputStream(new File(strSDpath, "bus.jpg"));
            while((c = inFs2.read()) != -1){
                outFs2.write(c);
            }
            outFs2.close();
            inFs2.close();
            c = 0;
            FileOutputStream outFs3 = new FileOutputStream(new File(strSDpath, "cat.jpg"));
            while((c = inFs3.read()) != -1){
                outFs3.write(c);
            }
            outFs3.close();
            inFs3.close();
            c = 0;
            FileOutputStream outFs4 = new FileOutputStream(new File(strSDpath, "dog.jpg"));
            while((c = inFs4.read()) != -1){
                outFs4.write(c);
            }
            outFs4.close();
            inFs4.close();
            c = 0;
            FileOutputStream outFs5 = new FileOutputStream(new File(strSDpath, "eagle.jpg"));
            while((c = inFs5.read()) != -1){
                outFs5.write(c);
            }
            outFs5.close();
            inFs5.close();
            c = 0;
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
                    100);
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
            case 100:

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

}