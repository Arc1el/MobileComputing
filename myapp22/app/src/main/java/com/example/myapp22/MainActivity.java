package com.example.myapp22;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ImageView btn_prev, btn_play, btn_next;
    SeekBar seekBar;
    TextView tv_title, tv_elapse, tv_remain;
    MediaPlayer mPlayer;
    Boolean flag_play;
    int cursor = 0;
    String[] songs = {"binu", "love", "starlight"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_prev = (ImageView) findViewById(R.id.btn_prev);
        btn_play = (ImageView) findViewById(R.id.btn_play);
        btn_next = (ImageView) findViewById(R.id.btn_next);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_elapse = (TextView) findViewById(R.id.tv_elapsed_time);
        tv_remain = (TextView) findViewById(R.id.tv_remaining_time);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        lv = (ListView) findViewById(R.id.listview);

        ArrayList<String> items = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_single_choice, items);
        lv.setAdapter(adapter);

        items.add(songs[0]); items.add(songs[1]); items.add(songs[2]);
        adapter.notifyDataSetChanged();

        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.binu);
        tv_title.setText(songs[cursor]);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPlayer = null;
                flag_play = false;
                SimpleDateFormat time_format = new SimpleDateFormat("mm:ss");

                cursor = i;
                int id = get_id(songs[cursor]);
                Uri song = Uri.parse("android.resource://" + getPackageName() + "/" + id);
                mPlayer = MediaPlayer.create(getApplicationContext(), song);

                tv_title.setText(songs[cursor]);
                tv_remain.setText(String.format(time_format.format(mPlayer.getDuration())));
                seekBar.setProgress(mPlayer.getCurrentPosition());
                tv_elapse.setText(String.format(time_format.format(mPlayer.getCurrentPosition())));
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btn_play.setOnClickListener((v) -> {
            if(!flag_play){
                mPlayer.start();
                flag_play = true;
                make_thread();
            }else{
                mPlayer.pause();
                flag_play = false;
            }
        });

        btn_prev.setOnClickListener((v) -> {
            flag_play = false;
            cursor --;
            tv_title.setText(songs[cursor]);
            if(cursor == -1){
                cursor = 3;
            }
            int id = get_id(songs[cursor]);
            Uri song = Uri.parse("android.resource://" + getPackageName() + "/" + id);
            mPlayer = MediaPlayer.create(getApplicationContext(), song);
            play_click();
        });

        btn_next.setOnClickListener((v) -> {
            flag_play = false;
            cursor ++;
            tv_title.setText(songs[cursor]);
            if(cursor == 3){
                cursor = 0;
            }
            int id = get_id(songs[cursor]);
            Uri song = Uri.parse("android.resource://" + getPackageName() + "/" + id);
            mPlayer = MediaPlayer.create(getApplicationContext(), song);
            play_click();
        });

    }
    void play_click() {
        if(!flag_play){
            mPlayer.start();
            make_thread();
            flag_play = true;
        }else {
            mPlayer.pause();
            flag_play = false;
        }
    }

    void make_thread(){
        new Thread(){
            public void run(){
                final SimpleDateFormat time_format = new SimpleDateFormat("mm:ss");
                if(mPlayer == null){
                    return;
                }
                seekBar.setMax(mPlayer.getDuration());

                while(mPlayer.isPlaying()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_remain.setText(String.format(time_format.format(mPlayer.getDuration())));
                            seekBar.setProgress(mPlayer.getCurrentPosition());
                            tv_elapse.setText(String.format(time_format.format(mPlayer.getCurrentPosition())));
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seekBar.setProgress(0);
                        tv_elapse.setText(String.format(time_format.format(0)));

                        cursor ++;
                        if(cursor == 3){
                            cursor = 0;
                        }

                        tv_title.setText(songs[cursor]);
                        flag_play = false;
                        int id = get_id(songs[cursor]);
                        Uri song = Uri.parse("android.resource://" + getPackageName() + "/" + id);
                        mPlayer = MediaPlayer.create(getApplicationContext(), song);
                        play_click();

                    }
                });
            }
        }.start();
    }
    public int get_id(String title){
        String package_name = this.getPackageName();
        int id = this.getResources().getIdentifier(title, "raw", package_name);
        return id;
    }
}