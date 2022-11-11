package com.example.visitour.Beans;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("id")
    public String mId;
    @SerializedName("nombre")
    public String mNombre;
    @SerializedName("apellido")
    public String mApellido;
    @SerializedName("nacionalidad")
    public String mPais;
    @SerializedName("telefono")
    public String mTelefono;
    @SerializedName("correo")
    public String mCorreo;

    public Usuario(String mId, String mNombre, String mApellido, String mPais, String mTelefono, String mCorreo) {
        this.mId = mId;
        this.mNombre = mNombre;
        this.mApellido = mApellido;
        this.mPais = mPais;
        this.mTelefono = mTelefono;
        this.mCorreo = mCorreo;
    }
}
