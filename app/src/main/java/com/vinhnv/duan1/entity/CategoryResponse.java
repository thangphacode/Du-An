package com.vinhnv.duan1.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {

    @SerializedName("count")
    private int count;
    @SerializedName("response_time")
    private String response_time;
    @SerializedName("items")
    private ArrayList<ItemsBean> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getResponse_time() {
        return response_time;
    }

    public void setResponse_time(String response_time) {
        this.response_time = response_time;
    }

    public ArrayList<ItemsBean> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemsBean> items) {
        this.items = items;
    }



    public static class ItemsBean {

        @SerializedName("count_new")
        private int count_new;
        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;

        public int getCount_new() {
            return count_new;
        }

        public void setCount_new(int count_new) {
            this.count_new = count_new;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "count=" + count +
                ", response_time='" + response_time + '\'' +
                ", items=" + items +
                '}';
    }


}
