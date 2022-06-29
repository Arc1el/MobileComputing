package com.example.myapp23;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mBtnPlay;
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnPlay = findViewById(R.id.btn);
    }

    // PLAY 버튼 이벤트
    public void onClick(View view) {
        // MediaPlayer 없으면 초기화 진행
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.test);	// 출력할 음악 파일 세팅
            // 음악 파일 재생이 완료됐을 때 호출될 콜백 세팅
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    // null 처리 및 버튼 텍스트 PLAY로 변경
                    mMediaPlayer = null;
                    mBtnPlay.setText("PLAY");
                }
            });
        }

        isPlaying();
    }

    // 플레이 체크
    private void isPlaying() {
        if (mMediaPlayer.isPlaying()) {	// 음악 파일 플레이중인 상태
            // 음악 파일 재생 멈추고 버튼 텍스트 PLAY로 변경
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
            mBtnPlay.setText("PLAY");
        } else {	// 음악 파일 재생중이 아닌 상태
            // 음악 파일 재생하고 버튼 텍스트 STOP으로 변경
            mMediaPlayer.start();
            mBtnPlay.setText("STOP");
        }
    }

}