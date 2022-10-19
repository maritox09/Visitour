package com.example.visitour.MVP_FavButton.FavButtonModel;

import com.example.visitour.Apis.ApiClient;
import com.example.visitour.Apis.ItemsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavButtonModel implements IFavButtonModel{
    @Override
    public void UnFav(Integer id, Integer userId) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<String> itemCall = mApi.unfavItem(id,userId);
        itemCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    @Override
    public void Fav(Integer id, Integer userId) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<String> itemCall = mApi.favItem(id,userId);
        itemCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
