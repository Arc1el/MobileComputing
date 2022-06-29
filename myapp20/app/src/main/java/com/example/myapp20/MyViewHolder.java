package com.example.myapp20;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv1, tv2, tv3;
    ImageView imageView;
    Button btn;

    public MyViewHolder(View itemView) {
        super(itemView);

        tv1 = (TextView) itemView.findViewById(R.id.item_title);
        tv2 = (TextView) itemView.findViewById(R.id.item_description);
        tv3 = (TextView) itemView.findViewById(R.id.item_price);
        imageView = (ImageView) itemView.findViewById(R.id.item_imageview);
        btn = (Button) itemView.findViewById(R.id.item_button);

    }

}