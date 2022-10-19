package com.example.visitour.MVP_Details.DetailsPresenter;

import com.example.visitour.Beans.Item;
import com.example.visitour.MVP_Details.DetailsModel.DetailsModel;
import com.example.visitour.MVP_Details.DetailsModel.IDetailsModel;
import com.example.visitour.MVP_Details.DetailsView.IDetailsView;

public class DetailsPresenter implements IDetailsPresenter{

    private IDetailsView detailsView;
    private IDetailsModel detailsModel;

    public DetailsPresenter(IDetailsView detailsView) {
        this.detailsView = detailsView;
        this.detailsModel = new DetailsModel(this);
    }

    @Override
    public void GetDetails(Integer mId, Integer mUserId) {
        detailsModel.GetDetails(mId, mUserId);
    }

    @Override
    public void OnDetailSuccess(Item item) {
        detailsView.OnDetailSuccess(item);
    }

    @Override
    public void OnDetailFailure(String msg) {
        detailsView.OnDetailFailure(msg);
    }
}
