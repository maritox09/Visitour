package com.example.visitour.usuario;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellido")
    private String apellido;
    @SerializedName("correo")
    private String correo;
    @SerializedName("password")
    private String password;
    private Integer id;

    public Usuario(String mNombre, String mApellido, String mCorreo, String mPass, Integer mId) {
        this.nombre = mNombre;
        this.apellido = mApellido;
        this.correo = mCorreo;
        this.id = mId;
        this.password = mPass;
    }

    public Usuario(String mNombre, String mApellido, String mCorreo, String mNacionalidad) {
        this.nombre = mNombre;
        this.apellido = mApellido;
        this.correo = mCorreo;
        this.password = mNacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
