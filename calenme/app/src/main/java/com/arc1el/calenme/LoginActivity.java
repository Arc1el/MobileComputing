package com.arc1el.calenme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText et_id, et_pw;
    Button btn_login;

    public LoginActivity() throws MalformedURLException {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                new Thread(){
                    @Override
                    public void run() {
                        Log.d("hyeon", "crawl start");
                        try {
                            String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.61 Safari/537.36";
                            Map<String, String> data = new HashMap<>();
                            data.put("userId", "20197125");
                            data.put("password", "19970514");

                            Connection.Response response = Jsoup.connect("https://cyber.hanbat.ac.kr/User.do?cmd=loginUser")
                                    .userAgent(userAgent)
                                    .timeout(3000)
                                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                                    .header("Content-Type", "application/x-www-form-urlencoded")
                                    .header("Accept-Encoding", "gzip, deflate, br")
                                    .header("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4")
                                    .data(data)
                                    .method(Connection.Method.POST)
                                    .execute();

                            Map<String, String> cookies = response.cookies();
                            Log.d("hyeon", cookies.toString());

                            Document mainPage = Jsoup.connect("https://cyber.hanbat.ac.kr/Study.do?cmd=viewStudyMyClassroom&boardInfoDTO.boardInfoGubun=myclassroom")
                                    .userAgent(userAgent)
                                    .header("Referer", "https://cyber.hanbat.ac.kr/Main.do?cmd=viewHome")
                                    .header("Host", "cyber.hanbat.ac.kr")
                                    .header("Connection", "keep-alive")
                                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.61 Safari/537.36")
                                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                                    .header("Content-Type", "application/x-www-form-urlencoded")
                                    .header("Accept-Encoding", "gzip, deflate, sdch")
                                    .header("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4")
                                    .cookies(cookies) // 위에서 얻은 '로그인 된' 쿠키
                                    //.header("Cookie", "_ga=GA1.3.1031242095.1639461590; _xm_webid_1_=-116010736; key=value; RSN_JSESSIONID=baa3rxeTVHninfzqCfNeyGLfzPhvWgsbN0LdnrS5TqhdDaejROLFL-DfKndu; imagePopSet=done; uset_check=20197125")
                                    .get();

                            Elements ele = mainPage.select("#timeline > li > .content");


                            Log.d("hyeon", mainPage.toString());
                            Log.d("hyeon", "crawl end");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });
    }
}
