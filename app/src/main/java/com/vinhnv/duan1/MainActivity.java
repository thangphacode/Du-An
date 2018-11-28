package com.vinhnv.duan1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.vinhnv.duan1.adapter.CategoryAdapter;
import com.vinhnv.duan1.common.Constants;
import com.vinhnv.duan1.entity.CategoryResponse;
import com.vinhnv.duan1.fragments.HomeFragment;
import com.vinhnv.duan1.services.ConnectServer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static Drawer drawer;

    public static Drawer getDrawer() {
        return drawer;
    }

    private Toolbar toolbar;

    public Toolbar getToolbar() {
        return toolbar;
    }

    private ArrayList<CategoryResponse.ItemsBean> listCategory;

    private RecyclerView rcCategory;

    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        listCategory = new ArrayList<>();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).addToBackStack("HomeFragment").commit();
        setupDrawerLayout();
        initSideMenu();
        getCategory();
    }

    private void getCategory() {
        ConnectServer.getResponseAPI().getCategory(Constants.WIDTH, Constants.HEIGHT).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    CategoryResponse categoryResponse = response.body();
                    listCategory = categoryResponse.getItems();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rcCategory.setLayoutManager(layoutManager);
                    categoryAdapter = new CategoryAdapter(MainActivity.this, listCategory);
                    rcCategory.setAdapter(categoryAdapter);
                    //sendActionToFragment(listCategory.get(0).getId());
                } else {
                    MyUtlis.showToast(getApplicationContext(), "Get caterogy lỗi");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("dulieutrave", t.toString() + "");
            }
        });
    }

    public void sendActionToFragment(int idCategory) {
        HomeFragment fragment = new HomeFragment();
        ((HomeFragment) fragment).getListImageFromCategory(idCategory);
    }

    private void initSideMenu() {
        rcCategory = (RecyclerView) findViewById(R.id.rcCategory);
    }

    private void setupDrawerLayout() {
        LayoutInflater inflater = getLayoutInflater();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_container)
                .withDrawerGravity(Gravity.LEFT)
                .withDisplayBelowStatusBar(false)
                .withDrawerWidthPx(Integer.parseInt(String.valueOf("" + MyUtlis.getScreenWidth() * 3 / 4)))
                .withCustomView(inflater.inflate(R.layout.side_menu, null))
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                    }
                })
                .build();
    }
}
