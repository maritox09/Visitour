package com.example.visitour.MVP_Item.ItemPresenter;

import com.example.visitour.Beans.Item;

import java.util.List;

public interface IItemPresenter {

    void GetLugares(Integer id);
    void GetRestaurantes(Integer id);
    void GetFavoritos(Integer id);
    void GetNear(Integer id, Float longitud, Float latitud);
    void OnItemSuccess(List<Item> mItems);
    void OnItemFailure(String msg);

}
