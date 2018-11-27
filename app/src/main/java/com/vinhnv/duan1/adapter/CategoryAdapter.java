package com.vinhnv.duan1.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinhnv.duan1.R;
import com.vinhnv.duan1.entity.CategoryResponse;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<CategoryResponse.ItemsBean> listItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName;
        View view;

        public MyViewHolder(View view) {
            super(view);
            tvCategoryName = (TextView) view.findViewById(R.id.tvItemTitle);
            this.view = view;
        }
    }

    public CategoryAdapter(Activity activity, ArrayList<CategoryResponse.ItemsBean> listItem) {
        this.context = activity;
        this.listItem = listItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d("size", listItem.size() + "");
        final CategoryResponse.ItemsBean entity = listItem.get(position);

        holder.tvCategoryName.setText(entity.getTitle());

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
