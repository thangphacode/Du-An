package com.vinhnv.duan1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.vinhnv.duan1.MainActivity;
import com.vinhnv.duan1.MyUtlis;
import com.vinhnv.duan1.R;
import com.vinhnv.duan1.adapter.ImagesAdapter;
import com.vinhnv.duan1.common.Constants;
import com.vinhnv.duan1.entity.ImagesResponse;
import com.vinhnv.duan1.services.ConnectServer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private Toolbar toolbar;

    private Drawer drawer;

    private Context context;

    private View rootView;

    private ImageView imgMenu, imgSearch;

    private RecyclerView rcImages;

    private ArrayList<ImagesResponse.ItemsBean> listImages;

    private ImagesAdapter imagesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fg_home, container, false);
        toolbar = rootView.findViewById(R.id.toolbar);
        rcImages = rootView.findViewById(R.id.rcImages);
        rcImages.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        listImages = new ArrayList<>();
        initToolbar(toolbar);
        drawer = ((MainActivity) getActivity()).getDrawer();
        context = rootView.getContext();
        //initController(rootView);
        getListImageFromCategory(1);
        return rootView;
    }

    public void getListImageFromCategory(int idCategory) {
        ConnectServer.getResponseAPI().getImageFromCategory(Constants.WIDTH, Constants.HEIGHT, idCategory, "date").enqueue(new Callback<ImagesResponse>() {
            @Override
            public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                if (response.isSuccessful()) {
                    ImagesResponse imagesResponse = response.body();
                    listImages = imagesResponse.getItems();
                    imagesAdapter = new ImagesAdapter(getActivity(), listImages);
                    rcImages.setAdapter(imagesAdapter);
                } else {
                    MyUtlis.showToast(getActivity(), "Get caterogy lỗi");
                }
            }

            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {
                Log.e("dulieutrave", t.toString() + "");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    private void initToolbar(View v) {
        imgMenu = v.findViewById(R.id.imgMenu);
        imgSearch = v.findViewById(R.id.imgSearch);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
        TextView tb = v.findViewById(R.id.txtToolbarTitle);
        tb.setText("Hình Nền");
    }
}
