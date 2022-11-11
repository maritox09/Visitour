package com.example.visitour.MVP_Perfil.PerfilPresenter;

import com.example.visitour.Beans.Usuario;

public interface IPerfilPresenter {
    void getData(int id);
    void saveData(int id, String nombre, String apellido, String correo, String nacionalidad, String telefono);
    void onDataSuccess(Usuario usuario);
    void onDataFailure(String msg);
}
