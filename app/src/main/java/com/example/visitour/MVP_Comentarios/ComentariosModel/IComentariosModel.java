package com.example.visitour.MVP_Comentarios.ComentariosModel;

public interface IComentariosModel {
    void GetComentarios(Integer id, Integer userId);
    void ComentarioNuevo(Integer id, Integer userId, String comentario);
}
