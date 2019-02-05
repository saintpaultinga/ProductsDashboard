package com.icc.commerce.productsdashboard.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Item {
    private String title;
    @SerializedName("target")
    private String url;

    public Item(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(getTitle(), item.getTitle()) &&
                Objects.equals(getUrl(), item.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getUrl());
    }

    @Override
    public String toString() {
        return "Item{" + "title='" + title + '\'' + ", url='" + url + '\'' + '}';
    }
}
