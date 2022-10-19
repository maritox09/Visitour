package com.example.visitour.MVP_Details.DetailsPresenter;

import com.example.visitour.Beans.Item;

public interface IDetailsPresenter {
    void GetDetails(Integer mId, Integer mUserId);
    void OnDetailSuccess(Item item);
    void OnDetailFailure(String msg);
}
