package com.vinhnv.duan1.services;


import com.vinhnv.duan1.entity.CategoryResponse;
import com.vinhnv.duan1.entity.ImagesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ServerAPI {

    @GET("/categories")
    Call<CategoryResponse> getCategory(@Query("screen[width]") int width,
                                                  @Query("screen[height]") int height);

    @GET("/images")
    Call<ImagesResponse> getImageFromCategory(@Query("screen[width]") int width,
                                              @Query("screen[height]") int height,
                                              @Query("category_id") int categoryId,
                                              @Query("sort") String sort);
}


