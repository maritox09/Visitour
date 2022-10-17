package com.example.visitour.Beans;

import com.google.gson.annotations.SerializedName;

public class Item{
    @SerializedName("nombre")
    public String mNombre;
    @SerializedName("url")
    public String mImageUrl;
    @SerializedName("depto")
    public String mDepto;
    @SerializedName("rating")
    public Integer mRating;

    public Item() {
    }

    public Item(String mNombre, String mImageUrl, String mDepto) {
        this.mNombre = mNombre;
        this.mImageUrl = mImageUrl;
        this.mDepto = mDepto;
    }

    public String getmNombre() {
        return mNombre;
    }

    public void setmNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public String getmDepto() {
        return mDepto;
    }

    public void setmDepto(String mDepto) {
        this.mDepto = mDepto;
    }

    public Integer getmRating() {
        return mRating;
    }

    public void setmRating(Integer mRating) {
        this.mRating = mRating;
    }
}
