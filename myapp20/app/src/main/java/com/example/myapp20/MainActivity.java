package com.example.myapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layout_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layout_manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout_manager);

        ArrayList myDataset=new ArrayList();
        myDataset.add(new PaintTitle(R.drawable.apple, "apple", "이 제품은 사과입니다. 산지직송 신선도 100%", "2000원"));
        myDataset.add(new PaintTitle(R.drawable.bus, "bus", "이 제품은 버스입니다. 대전 시내버스 모델!!", "135000000원"));
        myDataset.add(new PaintTitle(R.drawable.carpet, "carpet", "이 제품은 카펫입니다. 먼지날림 없음!", "42000원"));

        adapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(adapter);
    }
}