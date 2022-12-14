package com.example.visitour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.visitour.Beans.Item;
import com.example.visitour.Adapters.ItemsAdapter;
import com.example.visitour.MVP_Item.ItemPresenter.IItemPresenter;
import com.example.visitour.MVP_Item.ItemPresenter.ItemPresenter;
import com.example.visitour.MVP_Item.ItemView.IItemView;
import com.example.visitour.databinding.ActivityItemsBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemsActivity extends AppCompatActivity implements IItemView {

    private List<Item> mItems;
    private boolean order_asc, lugares;
    private ItemsAdapter adapter;
    private IItemPresenter itemPresenter;
    private ActivityItemsBinding binding;
    private Spinner spinnerAtt, spinnerOrd;
    private String[] att = {"Popularidad", "Nombre", "Departamento"};
    private String[] ord = {"Descendente","Ascendente"};
    SharedPreferences preferences;
    private String tab = "lug", aspecto = "Populadirad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        itemPresenter = new ItemPresenter(this);

        RecyclerView rvItems = findViewById(R.id.rv_lugares);
        adapter = new ItemsAdapter(new ArrayList<>());
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        binding.navigationBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.ic_lugares:
                    itemPresenter.GetLugares(preferences.getInt("userId",0));
                    tab = "lug";
                    ordenar();
                    break;
                case R.id.ic_restaurantes:
                    itemPresenter.GetRestaurantes(preferences.getInt("userId",0));
                    tab = "res";
                    ordenar();
                    break;
                case R.id.ic_favoritos:
                    itemPresenter.GetFavoritos(preferences.getInt("userId",0));
                    tab = "fav";
                    ordenar();
                    break;
                case R.id.ic_perfil:
                    break;
            }
            return true;
        });

        spinnerAtt = binding.spinnerAtt;
        spinnerOrd = binding.spinnerOrd;
        ArrayAdapter<String> arrayAdapterAtt = new ArrayAdapter<String>(ItemsActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,att);
        ArrayAdapter<String> arrayAdapterOrd = new ArrayAdapter<String>(ItemsActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ord);

        arrayAdapterAtt.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        arrayAdapterOrd.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);

        spinnerAtt.setAdapter(arrayAdapterAtt);
        spinnerOrd.setAdapter(arrayAdapterOrd);

        itemPresenter.GetLugares(preferences.getInt("userId",0));
        lugares = true;

        spinnerAtt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NewApi")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aspecto = parent.getItemAtPosition(position).toString();
                ordenar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOrd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NewApi")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String orden = parent.getItemAtPosition(position).toString();
                switch (orden){
                    case "Descendente":
                        try {
                            if(order_asc){
                                Collections.reverse(mItems);
                                adapter.reloadData(mItems);
                                order_asc = false;
                            }
                        }catch (Exception e){}
                        break;
                    case "Ascendente":
                        if(!order_asc){
                            Collections.reverse(mItems);
                            adapter.reloadData(mItems);
                            order_asc = true;
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void OnItemSuccess(List<Item> mItems) {
        this.mItems = mItems;
        order_asc = false;
        adapter.reloadData(mItems);
        adapter.Ord_Rat_Asc(false);
    }

    @Override
    public void OnItemFailure(String msg) {
        Toast.makeText(ItemsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(tab == "lug"){
            itemPresenter.GetLugares(preferences.getInt("userId",0));
        } else if (tab == "res"){
            itemPresenter.GetRestaurantes(preferences.getInt("userId",0));
        } else if (tab == "fav"){
            itemPresenter.GetFavoritos(preferences.getInt("userId",0));
        }
        ordenar();
    }

    private void ordenar(){
        switch (aspecto){
            case "Popularidad":
                if(order_asc){
                    try {
                        adapter.Ord_Rat_Asc(true);
                    } catch (Exception e){break;}
                } else {
                    try {
                        adapter.Ord_Rat_Asc(false);
                    } catch (Exception e) {}
                }
                break;
            case "Nombre":
                if(order_asc){
                    adapter.Ord_Nom_Asc(true);
                } else {
                    adapter.Ord_Nom_Asc(false);
                }
                break;
            case "Departamento":
                if(order_asc){
                    adapter.Ord_Dep_Asc(true);
                } else {
                    adapter.Ord_Dep_Asc(false);
                }
                break;
        }
    }
}