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
        myDataset.add(new PaintTitle(R.drawable.apple, "apple"));
        myDataset.add(new PaintTitle(R.drawable.bus, "bus"));

        adapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(adapter);


    }
}