package com.example.visitour.MVP_Item.ItemModel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Toast;

import com.example.visitour.Beans.Item;
import com.example.visitour.MVP_Item.ItemPresenter.IItemPresenter;
import com.example.visitour.R;
import com.example.visitour.REST.ApiClient;
import com.example.visitour.REST.ItemsApi;

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
    public void GetLugares() {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<List<Item>> bookCall = mApi.getLugares();
        bookCall.enqueue(new Callback<List<Item>>() {
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
    public void GetRestaurantes() {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<List<Item>> bookCall = mApi.getRestaurantes();
        bookCall.enqueue(new Callback<List<Item>>() {
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
}
