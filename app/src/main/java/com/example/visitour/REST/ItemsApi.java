package com.example.visitour.REST;

import com.example.visitour.Model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemsApi {
    @GET("/lugares.php")
    Call<List<Item>> getLugares();

    @GET("/restaurantes.php")
    Call<List<Item>> getRestaurantes();
}
