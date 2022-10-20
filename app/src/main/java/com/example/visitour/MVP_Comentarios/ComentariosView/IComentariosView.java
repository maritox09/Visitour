package com.example.visitour.MVP_Comentarios.ComentariosView;

import com.example.visitour.Beans.Comentario;

import java.util.List;

public interface IComentariosView {
    void OnComentariosSuccess(List<Comentario> comentarios);
    void OnComentariosFailure(String msg);
    void EnviarComentario(Integer id, Integer userId, String comentario);
    void ComentarioRegistrado();
}
