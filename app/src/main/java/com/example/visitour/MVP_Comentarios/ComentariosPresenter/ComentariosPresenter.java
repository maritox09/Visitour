package com.example.visitour.MVP_Comentarios.ComentariosPresenter;

import com.example.visitour.Beans.Comentario;
import com.example.visitour.MVP_Comentarios.ComentariosModel.ComentariosModel;
import com.example.visitour.MVP_Comentarios.ComentariosModel.IComentariosModel;
import com.example.visitour.MVP_Comentarios.ComentariosView.IComentariosView;

import java.util.List;

public class ComentariosPresenter implements IComentariosPresenter{

    IComentariosModel comentariosModel;
    IComentariosView comentariosView;

    public ComentariosPresenter(IComentariosView comentariosView) {
        this.comentariosView = comentariosView;
        this.comentariosModel = new ComentariosModel(this);
    }

    @Override
    public void GetComentarios(Integer id, Integer userId) {
        comentariosModel.GetComentarios(id,userId);
    }

    @Override
    public void ComentarioNuevo(Integer id, Integer userId, String comentario) {
        comentariosModel.ComentarioNuevo(id,userId,comentario);
    }

    @Override
    public void OnComentariosSuccess(List<Comentario> comentarios) {
        comentariosView.OnComentariosSuccess(comentarios);
    }

    @Override
    public void OnComentariosFailure(String msg) {
        comentariosView.OnComentariosFailure(msg);
    }

    @Override
    public void ComentarioRegistrado() {
        comentariosView.ComentarioRegistrado();
    }
}
