package com.example.visitour.MVP_Item.ItemView;

import com.example.visitour.Beans.Item;

import java.util.List;

public interface IItemView {

    void OnItemSuccess(List<Item> mItems);
    void OnItemFailure(String msg);

}
