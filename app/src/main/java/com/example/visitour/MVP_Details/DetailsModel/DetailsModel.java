package com.example.visitour.MVP_Details.DetailsModel;

import com.example.visitour.Apis.ApiClient;
import com.example.visitour.Apis.ItemsApi;
import com.example.visitour.Beans.Item;
import com.example.visitour.MVP_Details.DetailsPresenter.IDetailsPresenter;
import com.example.visitour.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsModel implements IDetailsModel{

    private IDetailsPresenter detailsPresenter;

    public DetailsModel(IDetailsPresenter detailsPresenter) {
        this.detailsPresenter = detailsPresenter;
    }

    @Override
    public void GetDetails(Integer mId, Integer mUserId) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<Item> itemCall = mApi.getDetalles(mId,mUserId);
        itemCall.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                detailsPresenter.OnDetailSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                detailsPresenter.OnDetailFailure(String.valueOf(R.string.toast_ErrorInterno));
            }
        });
    }
}
