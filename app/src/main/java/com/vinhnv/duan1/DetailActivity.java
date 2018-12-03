package com.vinhnv.duan1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.vinhnv.duan1.entity.ImagesResponse;

import java.util.ArrayList;

import javax.sql.DataSource;

public class DetailActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private ImageView imgBack;

    private ImageView imgSearch;

    private TextView txTitle;

    private ImageView imgDetail;

    private FloatingActionButton fap;

    private ProgressDialog progDialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_detail);
        Gson gson = new Gson();
        ImagesResponse.ItemsBean imagesResponse = gson.fromJson(getIntent().getStringExtra("detail"), ImagesResponse.ItemsBean.class);
        initControler(imagesResponse);
    }

    public void initControler(ImagesResponse.ItemsBean imagesResponse) {
        showProgressDialog();
        toolbar = findViewById(R.id.toolbar);
        imgBack = findViewById(R.id.imgMenu);
        imgBack.setImageResource(R.drawable.back);
        imgSearch = findViewById(R.id.imgSearch);
        imgSearch.setVisibility(View.INVISIBLE);
        txTitle = findViewById(R.id.txtToolbarTitle);
        imgDetail = findViewById(R.id.imgDetail);
        fap = findViewById(R.id.fab);
        txTitle.setText(imagesResponse.getAuthor());
        String imageLink = imagesResponse.getVariations().getAdapted().getUrl();
        Glide.with(this)
                .load(imageLink)
               .listener(new RequestListener<String, GlideDrawable>() {
                   @Override
                   public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                       dismissProgressDialog();
                       return false;
                   }

                   @Override
                   public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                       dismissProgressDialog();
                       return false;
                   }
               }).into(imgDetail);

        fap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        return;
    }

    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(DetailActivity.this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("Đang tải ảnh");
        progDialog.show();
    }

    private void dismissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }
}
