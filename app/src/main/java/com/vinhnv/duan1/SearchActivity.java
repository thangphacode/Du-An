package com.vinhnv.duan1;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vinhnv.duan1.adapter.ImagesAdapter;
import com.vinhnv.duan1.common.Constants;
import com.vinhnv.duan1.entity.ImagesResponse;
import com.vinhnv.duan1.services.ConnectServer;
import com.vinhnv.duan1.services.SaveImageHelper;

import java.util.ArrayList;
import java.util.UUID;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private ProgressDialog progDialog = null;

    private EditText edtSearch;

    private Button btnSearch;

    private Toolbar toolbar;

    private ImageView imgMenu, imgSearch;

    private TextView tb;

    private RecyclerView rcImageSearch;

    private ArrayList<ImagesResponse.ItemsBean> listImages;
    private ImagesAdapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_search);
        initToolbar();
        initControler();
    }

    public void initControler() {
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rcImageSearch = findViewById(R.id.rcImagesSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtSearch.getText().toString().equals("")) {
                    searchImages(edtSearch.getText().toString());
                }
            }
        });
    }

    private void searchImages(String textSearch) {
        showProgressDialog();
        ConnectServer.getResponseAPI().searchImages(textSearch, Constants.WIDTH, Constants.HEIGHT, "date").enqueue(new Callback<ImagesResponse>() {
            @Override
            public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                if (response.isSuccessful()) {
                    ImagesResponse imagesResponse = response.body();
                    listImages = imagesResponse.getItems();
                    rcImageSearch.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                    imagesAdapter = new ImagesAdapter(SearchActivity.this, listImages);
                    rcImageSearch.setAdapter(imagesAdapter);
                    imagesAdapter.notifyDataSetChanged();
                    dismissProgressDialog();
                } else {
                    dismissProgressDialog();
                    MyUtlis.showToast(SearchActivity.this, "tim kiếm lỗi");
                }
            }

            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {
                dismissProgressDialog();
            }
        });
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        imgMenu = findViewById(R.id.imgMenu);
        imgMenu.setImageResource(R.drawable.back);
        imgSearch = findViewById(R.id.imgSearch);
        imgSearch.setVisibility(View.INVISIBLE);
        tb = findViewById(R.id.txtToolbarTitle);
        tb.setText("Tìm kiếm");
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
            progDialog = new ProgressDialog(SearchActivity.this);
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


