package com.example.visitour.Beans;

import com.google.gson.annotations.SerializedName;

public class Comentario {
    @SerializedName("id")
    public Integer id;
    @SerializedName("usuario")
    public String mUsuario;
    @SerializedName("comentario")
    public String mComentario;
    @SerializedName("borrar")
    public Boolean mBorrar;

    public Comentario(Integer id, String mUsuario, String mComentario, Boolean mBorrar) {
        this.id = id;
        this.mUsuario = mUsuario;
        this.mComentario = mComentario;
        this.mBorrar = mBorrar;
    }
}
