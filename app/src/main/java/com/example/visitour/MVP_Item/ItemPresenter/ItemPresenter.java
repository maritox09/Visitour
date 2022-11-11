package com.example.visitour.MVP_Item.ItemPresenter;

import com.example.visitour.Beans.Item;
import com.example.visitour.MVP_Item.ItemModel.IItemModel;
import com.example.visitour.MVP_Item.ItemModel.ItemModel;
import com.example.visitour.MVP_Item.ItemView.IItemView;

import java.util.List;

public class ItemPresenter implements IItemPresenter{

    IItemModel itemModel;
    IItemView itemView;

    public ItemPresenter(IItemView itemView){
        this.itemView = itemView;
        this.itemModel = new ItemModel(this);
    }

    @Override
    public void GetLugares(Integer id) {
        itemModel.GetLugares(id);
    }

    @Override
    public void GetRestaurantes(Integer id) {
        itemModel.GetRestaurantes(id);
    }

    @Override
    public void GetFavoritos(Integer id) {
        itemModel.GetFavoritos(id);
    }

    @Override
    public void GetNear(Integer id, Float longitud, Float latitud) {
        itemModel.GetNear(id,longitud,latitud);
    }

    @Override
    public void OnItemSuccess(List<Item> mItems) {
        itemView.OnItemSuccess(mItems);
    }

    @Override
    public void OnItemFailure(String msg) {
        itemView.OnItemFailure(msg);
    }
}
