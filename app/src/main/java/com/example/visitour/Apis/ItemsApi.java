package com.example.visitour.Apis;

import com.example.visitour.Beans.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ItemsApi {
    @FormUrlEncoded
    @POST("/lugares.php")
    Call<List<Item>> getLugares(@Field("userId") Integer id);

    @FormUrlEncoded
    @POST("/restaurantes.php")
    Call<List<Item>> getRestaurantes(@Field("userId") Integer id);

    @FormUrlEncoded
    @POST("/favoritos.php")
    Call<List<Item>> getFavoritos(@Field("userId") Integer id);

    @FormUrlEncoded
    @POST("/detalles.php")
    Call<Item> getDetalles(@Field("id") Integer id, @Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("/fav.php")
    Call<String> favItem(@Field("id") Integer id, @Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("/unfav.php")
    Call<String> unfavItem(@Field("id") Integer id, @Field("userId") Integer userId);
}
