package com.example.myapp13;

import android.content.ClipData;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp13.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    RadioGroup animal_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*
        View header = navigationView.getHeaderView(0);
        tv1 = (TextView) header.findViewById(R.id.draw_tv_name);
        tv2 = (TextView) header.findViewById(R.id.draw_tv_email);
        header_image_view = (ImageView) header.findViewById(R.id.imageView);

        Menu menu = navigationView.getMenu();
        menu_item = menu.getItem(R.id.nav_gallery);
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if( id == R.id.login){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("사용자 입력");
            dlg.setIcon(R.mipmap.ic_launcher_round);

            View dialogview;
            dialogview = View.inflate(this, R.layout.dialog_view, null);
            dlg.setView(dialogview);

            dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
                    Menu menu = nav_view.getMenu();
                    MenuItem nav_item = menu.findItem(R.id.nav_gallery);
                    nav_item.setTitle("logout");

                    ImageView imgview1 = (ImageView) findViewById(R.id.imageView);
                    animal_group = (RadioGroup) dialogview.findViewById(R.id.avatar_radio_group);

                    if(animal_group.getCheckedRadioButtonId() == R.id.radio_cat)
                        imgview1.setImageResource(R.drawable.cat);
                    else if (animal_group.getCheckedRadioButtonId() == R.id.radio_dog)
                        imgview1.setImageResource(R.drawable.dog);
                    else
                        imgview1.setImageResource(R.drawable.horse);

                    TextView tv_name = (TextView) findViewById(R.id.draw_tv_name);
                    EditText dialog_name = (EditText) dialogview.findViewById(R.id.dialog_edit_name);
                    String str1 = dialog_name.getText().toString();
                    tv_name.setText(str1);

                    TextView tv_email = (TextView) findViewById(R.id.draw_tv_email);
                    EditText dialog_email = (EditText) dialogview.findViewById(R.id.dialog_edit_email);
                    String str2 = dialog_email.getText().toString();
                    tv_email.setText(str2);

                }
            });
            dlg.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            dlg.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void set_default(){
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = nav_view.getMenu();
        MenuItem nav_item = menu.findItem(R.id.nav_gallery);
        nav_item.setTitle("Gallery");

        ImageView imgview1 = (ImageView) findViewById(R.id.imageView);
        imgview1.setImageResource(R.mipmap.ic_launcher_round);

        TextView tv_name = (TextView) findViewById(R.id.draw_tv_name);
        tv_name.setText(R.string.nav_header_title);

        TextView tv_email = (TextView) findViewById(R.id.draw_tv_email);
        tv_email.setText(R.string.nav_header_subtitle);
    }
}