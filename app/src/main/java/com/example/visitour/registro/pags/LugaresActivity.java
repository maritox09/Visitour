package com.example.visitour.registro.pags;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.visitour.Model.Item;
import com.example.visitour.Model.ItemsAdapter;
import com.example.visitour.R;
import com.example.visitour.REST.ApiClient;
import com.example.visitour.REST.ItemsApi;
import com.example.visitour.databinding.ActivityLugaresBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LugaresActivity extends AppCompatActivity {

    private List<Item> mItems;
    private ItemsApi mApi;
    private boolean order_asc;
    ActivityLugaresBinding binding;
    Spinner spinnerAtt, spinnerOrd;
    String[] att = {"Popularidad", "Nombre", "Departamento"};
    String[] ord = {"Descendente","Ascendente"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLugaresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Comparator<Item> compareByNombre = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getmNombre().compareTo(o2.getmNombre());
            }
        };

        Comparator<Item> compareByRating = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getmRating().compareTo(o2.getmRating());
            }
        };

        Comparator<Item> compareByDepto = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getmDepto().compareTo(o2.getmDepto());
            }
        };


        mApi = ApiClient.getInstance().create(ItemsApi.class);

        RecyclerView rvBooks = findViewById(R.id.rv_lugares);
        // Create adapter passing in the sample user data
        ItemsAdapter adapter = new ItemsAdapter(new ArrayList<>());
        // Attach the adapter to the recyclerview to populate items
        rvBooks.setAdapter(adapter);
        // Set layout manager to position the items
        rvBooks.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Item>> bookCall = mApi.getLugares();
        bookCall.enqueue(new Callback<List<Item>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                mItems = response.body();
                Collections.sort(mItems,compareByRating);
                Collections.reverse(mItems);
                order_asc = false;
                adapter.reloadData(mItems);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(LugaresActivity.this, "Error al obtener los libros", Toast.LENGTH_SHORT).show();
            }
        });

        binding.navigationBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.ic_lugares:
                    break;
                case R.id.ic_restaurantes:
                    startActivity(new Intent(this,RestaurantesActivity.class));
                    break;
                case R.id.ic_perfil:
                    break;
            }
            return true;
        });

        spinnerAtt = binding.spinnerAtt;
        spinnerOrd = binding.spinnerOrd;
        ArrayAdapter<String> arrayAdapterAtt = new ArrayAdapter<String>(LugaresActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,att);
        ArrayAdapter<String> arrayAdapterOrd = new ArrayAdapter<String>(LugaresActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ord);

        arrayAdapterAtt.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        arrayAdapterOrd.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);

        spinnerAtt.setAdapter(arrayAdapterAtt);
        spinnerOrd.setAdapter(arrayAdapterOrd);

        spinnerAtt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NewApi")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String v = parent.getItemAtPosition(position).toString();
                switch (v){
                    case "Popularidad":
                        if(order_asc){
                            try {
                                Collections.sort(mItems,compareByRating);
                                adapter.reloadData(mItems);
                            } catch (Exception e){break;}
                        } else {
                            try {
                                Collections.sort(mItems,compareByRating.reversed());
                                adapter.reloadData(mItems);
                            } catch (Exception e) {}
                        }
                        break;
                    case "Nombre":
                        if(order_asc){
                            Collections.sort(mItems,compareByNombre.reversed());
                            adapter.reloadData(mItems);
                        } else {
                            Collections.sort(mItems,compareByNombre);
                            adapter.reloadData(mItems);
                        }
                        break;
                    case "Departamento":
                        if(order_asc){
                            Collections.sort(mItems,compareByDepto.reversed());
                            adapter.reloadData(mItems);
                        } else {
                            Collections.sort(mItems,compareByDepto);
                            adapter.reloadData(mItems);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOrd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NewApi")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String v = parent.getItemAtPosition(position).toString();
                switch (v){
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
}