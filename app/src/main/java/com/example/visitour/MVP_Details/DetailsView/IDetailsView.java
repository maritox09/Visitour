package com.example.visitour.MVP_Details.DetailsView;

import com.example.visitour.Beans.Item;

public interface IDetailsView {
    void OnDetailSuccess(Item item);
    void OnDetailFailure(String msg);
}
