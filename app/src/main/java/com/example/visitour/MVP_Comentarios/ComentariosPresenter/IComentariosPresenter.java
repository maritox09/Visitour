package com.example.visitour.MVP_Comentarios.ComentariosPresenter;

import com.example.visitour.Beans.Comentario;

import java.util.List;

public interface IComentariosPresenter {
    void GetComentarios(Integer id, Integer userId);
    void ComentarioNuevo(Integer id, Integer userId, String comentario);
    void OnComentariosSuccess(List<Comentario> comentarios);
    void OnComentariosFailure(String msg);
    void ComentarioRegistrado();
}
