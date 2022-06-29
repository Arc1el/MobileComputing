package com.example.myapp19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    EditText et;
    Button add_btn;
    Spinner spinner;
    ArrayList<PaintTitle> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.list_view);
        et = (EditText) findViewById(R.id.et);
        add_btn = (Button) findViewById(R.id.btn_add);
        spinner = (Spinner) findViewById(R.id.spinner);

        final String[] midNames = {"로스트", "가십걸", "빅뱅이론"};


        final ArrayList<String> midList = new ArrayList<String>();
        //final String[] mid = {"string1", "string2", "string3"};
        data = new ArrayList<PaintTitle>();
        data.add(new PaintTitle(R.drawable.apple, "apple"));
        data.add(new PaintTitle(R.drawable.bus, "bus"));

        MyBaseAdapter madapter = new MyBaseAdapter(this, data);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, midList);
        //lv.setAdapter(adapter);
        lv.setAdapter(madapter);

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, midNames);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinner_adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                midList.add(midNames[i]);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView adapterView) {
                Toast.makeText(getApplicationContext(), "nothing selected", Toast.LENGTH_SHORT).show();

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                midList.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midList.add(et.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
class PaintTitle {
    int imageId;
    String title;

    public PaintTitle(int id, String str) {
        imageId = id;
        title = str;
    }

}

class MyBaseAdapter extends BaseAdapter {

    Context mContext = null;
    ArrayList<PaintTitle> mData = null;

    public MyBaseAdapter(Context context, ArrayList<PaintTitle> data) {

        mContext = context;
        mData = data;

    }
    @Override
    public int getCount() {
        //   return mData.size();
        return 100;  // for test

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public PaintTitle getItem(int position) {
        return mData.get(position%2);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout;
        int newposition = position % 2; // for test

        if (convertView == null) {
            itemLayout = View.inflate(mContext, R.layout.listview_item, null);
            Log.d("hwang", "convertView=null pos="+position);
        } else {
            itemLayout = convertView;
            Log.d("hwang", "convertView!=null pos="+position);
        }


        ImageView imageView = (ImageView) itemLayout.findViewById(R.id.imageView);
        imageView.setImageResource(mData.get(newposition).imageId);

        TextView textView = (TextView) itemLayout.findViewById(R.id.textView);
        textView.setText(mData.get(newposition).title);
        return itemLayout;

    }
}