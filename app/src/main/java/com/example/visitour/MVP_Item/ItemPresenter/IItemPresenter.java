package com.example.visitour.MVP_Item.ItemPresenter;

import com.example.visitour.Beans.Item;

import java.util.List;

public interface IItemPresenter {

    void GetLugares();
    void GetRestaurantes();
    void OnItemSuccess(List<Item> mItems);
    void OnItemFailure(String msg);

}
