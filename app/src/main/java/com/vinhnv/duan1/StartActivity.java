package com.vinhnv.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.vinhnv.duan1.adapter.CategoryAdapter;
import com.vinhnv.duan1.common.Constants;
import com.vinhnv.duan1.entity.CategoryResponse;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class StartActivity extends AppCompatActivity {

    private ArrayList<CategoryResponse.ItemsBean> listCategory;

    private RecyclerView rcCategory;

    private CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_start);
        Constants.WIDTH = MyUtlis.getScreenWidth();
        Constants.HEIGHT = MyUtlis.getScreenHeight();
        new Timer().schedule(new TimerTask() {
            public void run() {

                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        }, 2000);
    }
}
