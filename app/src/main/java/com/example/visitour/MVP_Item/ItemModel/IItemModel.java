package com.example.visitour.MVP_Item.ItemModel;

public interface IItemModel {

    void GetLugares(Integer id);
    void GetRestaurantes(Integer id);
    void GetFavoritos(Integer id);
    void GetNear(Integer id, Float longitud, Float latitud);

}
