package com.example.visitour.MVP_Perfil.PerfilModel;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.example.visitour.Apis.ApiClient;
import com.example.visitour.Apis.ItemsApi;
import com.example.visitour.Beans.Usuario;
import com.example.visitour.MVP_Perfil.PerfilPresenter.IPerfilPresenter;
import com.example.visitour.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilModel implements IPerfilModel{

    IPerfilPresenter perfilPresenter;

    public PerfilModel(IPerfilPresenter perfilPresenter){
        this.perfilPresenter = perfilPresenter;
    }

    @Override
    public void getData(int id) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<Usuario> itemCall = mApi.getPerfil(id);
        itemCall.enqueue(new Callback<Usuario>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                perfilPresenter.onDataSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                perfilPresenter.onDataFailure(String.valueOf(R.string.toast_ErrorInterno));
            }
        });
    }

    @Override
    public void saveData(int id, String nombre, String apellido, String correo, String nacionalidad, String telefono) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<String> itemCall = mApi.savePerfil(id,nombre,apellido,correo,nacionalidad,telefono);
        itemCall.enqueue(new Callback<String>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                perfilPresenter.onDataFailure("Datos actualizados");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                perfilPresenter.onDataFailure("Datos actualizados");
            }
        });
    }
}
