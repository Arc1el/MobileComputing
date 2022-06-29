package com.example.myapp20;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<PaintTitle> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<PaintTitle> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.imageView.setImageResource(mDataset.get(position).imageId);
        holder.tv1.setText(mDataset.get(position).title);
        holder.tv2.setText(mDataset.get(position).description);
        holder.tv3.setText(mDataset.get(position).price);

        final Context mycontext = holder.itemView.getContext();
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                intent.putExtra("img_src", mDataset.get(position).imageId);
                intent.putExtra("desc", mDataset.get(position).description);
                intent.putExtra("price", mDataset.get(position).price);
                intent.putExtra("title", mDataset.get(position).title);
                mycontext.startActivity(intent);

            }
        });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewitem, parent, false);  // recyclerview
        Log.d("hyeon", "onCreateViewHolder");
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitem, parent, false);  // cardview
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size(); //
        //  return mDataset.size();
    }

}