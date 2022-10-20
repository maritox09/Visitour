package com.example.visitour.MVP_Comentarios.ComentariosModel;

import com.example.visitour.Apis.ApiClient;
import com.example.visitour.Apis.ItemsApi;
import com.example.visitour.Beans.Comentario;
import com.example.visitour.MVP_Comentarios.ComentariosPresenter.IComentariosPresenter;
import com.example.visitour.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentariosModel implements IComentariosModel{

    IComentariosPresenter comentariosPresenter;

    public ComentariosModel(IComentariosPresenter comentariosPresenter){
        this.comentariosPresenter = comentariosPresenter;
    }

    @Override
    public void GetComentarios(Integer id, Integer userId) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<List<Comentario>> itemCall = mApi.getComentarios(id,userId);
        itemCall.enqueue(new Callback<List<Comentario>>() {
            @Override
            public void onResponse(Call<List<Comentario>> call, Response<List<Comentario>> response) {
                comentariosPresenter.OnComentariosSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Comentario>> call, Throwable t) {
                comentariosPresenter.OnComentariosFailure(String.valueOf(R.string.toast_ErrorInterno));
            }
        });
    }

    @Override
    public void ComentarioNuevo(Integer id, Integer userId, String comentario) {
        ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
        Call<String> itemCall = mApi.comentarioNuevo(id,userId,comentario);
        itemCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                comentariosPresenter.ComentarioRegistrado();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                comentariosPresenter.ComentarioRegistrado();
            }
        });
    }
}
