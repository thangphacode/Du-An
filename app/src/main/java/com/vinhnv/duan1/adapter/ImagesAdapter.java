package com.vinhnv.duan1.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vinhnv.duan1.R;
import com.vinhnv.duan1.entity.ImagesResponse;

import java.util.ArrayList;


public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ImagesResponse.ItemsBean> listItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        View view;

        public MyViewHolder(View view) {
            super(view);
            imgView = (ImageView) view.findViewById(R.id.imgView);
            this.view = view;
        }
    }

    public ImagesAdapter(Activity activity, ArrayList<ImagesResponse.ItemsBean> listItem) {
        this.context = activity;
        this.listItem = listItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d("size", listItem.size() + "");
        final ImagesResponse.ItemsBean entity = listItem.get(position);
//        Glide.with(context).load(entity.getSource_link()).error(R.mipmap.ic_launcher).into(holder.imgView);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
