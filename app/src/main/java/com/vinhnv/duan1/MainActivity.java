package com.vinhnv.duan1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.vinhnv.duan1.adapter.CategoryAdapter;
import com.vinhnv.duan1.adapter.ImagesAdapter;
import com.vinhnv.duan1.common.Constants;
import com.vinhnv.duan1.entity.CategoryResponse;
import com.vinhnv.duan1.entity.ImagesResponse;
import com.vinhnv.duan1.services.ConnectServer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CallBackCategory {

    public static Drawer drawer;

    public static Drawer getDrawer() {
        return drawer;
    }

    private Toolbar toolbar;

    private ImageView imageLogin;


    public Toolbar getToolbar() {
        return toolbar;
    }

    private ArrayList<CategoryResponse.ItemsBean> listCategory;

    private RecyclerView rcCategory;

    private CategoryAdapter categoryAdapter;

    private RecyclerView rcImages;

    private ArrayList<ImagesResponse.ItemsBean> listImages;

    private ImagesAdapter imagesAdapter;

    private ImageView imgMenu, imgSearch;

    private TextView tb;

    private ProgressDialog progDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        listCategory = new ArrayList<>();
        setupDrawerLayout();
        initSideMenu();
        initToolbar();
        getCategory();
//        imgLogin();
        toolbar = findViewById(R.id.toolbar);
        rcImages = findViewById(R.id.rcImages);
        rcImages.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
    }
//
//    private void imgLogin() {
//        imageLogin = findViewById(R.id.imgLogin);
//        imageLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }


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
                    categoryAdapter.setOnCategoryClickedListener(MainActivity.this);
                    rcCategory.setAdapter(categoryAdapter);
                    getListImageFromCategory(listCategory.get(0).getId());
                    tb.setText(listCategory.get(0).getTitle());
                    layoutManager.scrollToPositionWithOffset(0, 0);
                } else {
                    MyUtlis.showToast(getApplicationContext(), "Get caterogy lỗi");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                dismissProgressDialog();
            }
        });
    }

    private void initToolbar() {
        imgMenu = findViewById(R.id.imgMenu);
        imgSearch = findViewById(R.id.imgSearch);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawer.isDrawerOpen()) {
                    drawer.openDrawer();
                } else {
                    drawer.closeDrawer();
                }
            }
        });
        tb = findViewById(R.id.txtToolbarTitle);
        tb.setText("Hình Nền");
    }

    public void getListImageFromCategory(int idCategory) {
        showProgressDialog();
        ConnectServer.getResponseAPI().getImageFromCategory(Constants.WIDTH, Constants.HEIGHT, idCategory, "date").enqueue(new Callback<ImagesResponse>() {
            @Override
            public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                if (response.isSuccessful()) {
                    ImagesResponse imagesResponse = response.body();
                    listImages = imagesResponse.getItems();
                    changeData();

                } else {
                    MyUtlis.showToast(MainActivity.this, "Get caterogy lỗi");
                    dismissProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {
                dismissProgressDialog();
            }
        });
    }

    public void changeData() {
        rcImages.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        imagesAdapter = new ImagesAdapter(MainActivity.this, listImages);
        rcImages.setAdapter(imagesAdapter);
        imagesAdapter.notifyDataSetChanged();
        dismissProgressDialog();
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

    @Override
    public void clickCategoryId(int categoryId, String title) {
        getListImageFromCategory(categoryId);
        tb.setText(title);
        drawer.closeDrawer();
    }

    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(MainActivity.this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("Đang tải dữ liệu");
        progDialog.show();
    }

    private void dismissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }
}
