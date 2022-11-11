package com.example.visitour.MVP_Item.ItemModel;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.example.visitour.Beans.Item;
import com.example.visitour.MVP_Item.ItemPresenter.IItemPresenter;
import com.example.visitour.R;
import com.example.visitour.Apis.ApiClient;
import com.example.visitour.Apis.ItemsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemModel implements IItemModel {

    private IItemPresenter itemPresenter;

    public ItemModel(IItemPresenter itemPresenter){
        this.itemPresenter = itemPresenter;
    }

    @Override
    public void GetLugares(Integer id) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<List<Item>> itemCall = mApi.getLugares(id);
        itemCall.enqueue(new Callback<List<Item>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                itemPresenter.OnItemSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                itemPresenter.OnItemFailure(String.valueOf(R.string.toast_ErrorInterno));
            }
        });
    }

    @Override
    public void GetRestaurantes(Integer id) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<List<Item>> itemCall = mApi.getRestaurantes(id);
        itemCall.enqueue(new Callback<List<Item>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                itemPresenter.OnItemSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                itemPresenter.OnItemFailure(String.valueOf(R.string.toast_ErrorInterno));
            }
        });
    }

    @Override
    public void GetFavoritos(Integer id) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<List<Item>> itemCall = mApi.getFavoritos(id);
        itemCall.enqueue(new Callback<List<Item>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                itemPresenter.OnItemSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                itemPresenter.OnItemFailure(String.valueOf(R.string.toast_ErrorInterno));
            }
        });
    }

    @Override
    public void GetNear(Integer id, Float longitud, Float latitud) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<List<Item>> itemCall = mApi.getNear(id,longitud,latitud);
        itemCall.enqueue(new Callback<List<Item>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                itemPresenter.OnNearSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                itemPresenter.OnItemFailure(String.valueOf(R.string.toast_ErrorInterno));
            }
        });
    }
}
