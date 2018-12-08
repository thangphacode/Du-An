package com.vinhnv.duan1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vinhnv.duan1.common.ImageStorage;
import com.vinhnv.duan1.entity.ImagesResponse;
import com.vinhnv.duan1.services.SaveImageHelper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import dmax.dialog.SpotsDialog;

public class DetailActivity extends AppCompatActivity  {
    private Context context;

    private Toolbar toolbar;

    private ImageView imgBack;

    private ImageView imgSearch;

    private TextView txTitle;

    private ImageView imgDetail;

    private FloatingActionButton fap;

    private ProgressDialog progDialog = null;

    private static final int PERMISSION_REQUEST_CODE = 1000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_detail);
        Gson gson = new Gson();
        ImagesResponse.ItemsBean imagesResponse = gson.fromJson(getIntent().getStringExtra("detail"), ImagesResponse.ItemsBean.class);
        initControler(imagesResponse);


    }

    public void initControler(final ImagesResponse.ItemsBean imagesResponse) {
        showProgressDialog();
        toolbar = findViewById(R.id.toolbar);
        imgBack = findViewById(R.id.imgMenu);
        imgBack.setImageResource(R.drawable.back);
        imgSearch = findViewById(R.id.imgSearch);
        imgSearch.setVisibility(View.INVISIBLE);
        txTitle = findViewById(R.id.txtToolbarTitle);
        imgDetail = findViewById(R.id.imgDetail);
        fap = findViewById(R.id.fab);
        if (imagesResponse.getAuthor().equals("")) {
            txTitle.setText("Hình Ảnh");
        } else {
            txTitle.setText(imagesResponse.getAuthor());
        }

        final String imageLink = imagesResponse.getVariations().getAdapted().getUrl();
        Glide.with(this)
                .load(imageLink)
                .dontTransform()
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
                if(ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(DetailActivity.this, "You should grant permission", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{

                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        },PERMISSION_REQUEST_CODE);
                    }
                    return;
                }
                else
                {
                    AlertDialog dialog = new SpotsDialog(DetailActivity.this);
                    dialog.show();
                    dialog.setMessage("Downloading...");

                    String fileName = UUID.randomUUID().toString()+".jpg";
                    Picasso.with(getBaseContext())
                            .load(imageLink)
                            .into(new SaveImageHelper(getBaseContext(),
                                    dialog,
                                    getApplicationContext().getContentResolver(),
                                    fileName,
                                    "Image description"));
                }
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case PERMISSION_REQUEST_CODE:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            break;
        }
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


