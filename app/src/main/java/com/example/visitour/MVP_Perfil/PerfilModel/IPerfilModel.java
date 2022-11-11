package com.example.visitour.MVP_Perfil.PerfilModel;

public interface IPerfilModel {
    void getData(int id);
    void saveData(int id, String nombre, String apellido, String correo, String nacionalidad, String telefono);
}
