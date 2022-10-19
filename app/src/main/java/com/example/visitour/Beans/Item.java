package com.example.visitour.Beans;

import com.google.gson.annotations.SerializedName;

public class Item{
    @SerializedName("id")
    public String mId;
    @SerializedName("nombre")
    public String mNombre;
    @SerializedName("url")
    public String mImageUrl;
    @SerializedName("depto")
    public String mDepto;
    @SerializedName("rating")
    public Float mRating;
    @SerializedName("descripcion")
    public String mDescripcion;
    @SerializedName("tipo")
    public String mTipo;
    @SerializedName("waze")
    public String mWaze;
    @SerializedName("maps")
    public String mMaps;
    @SerializedName("favorito")
    public Boolean mFavorito;

    public Item() {
    }

    public Item(String mId, String mNombre, String mImageUrl, String mDepto, Float mRating, String mDescripcion, String mTipo, String mWaze, String mMaps, Boolean mFavorito) {
        this.mId = mId;
        this.mNombre = mNombre;
        this.mImageUrl = mImageUrl;
        this.mDepto = mDepto;
        this.mRating = mRating;
        this.mDescripcion = mDescripcion;
        this.mTipo = mTipo;
        this.mWaze = mWaze;
        this.mMaps = mMaps;
        this.mFavorito = mFavorito;
    }

    public Item(String mId){
        this.mId = mId;
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

    public Float getmRating() {
        return mRating;
    }

    public void setmRating(Float mRating) {
        this.mRating = mRating;
    }
}
