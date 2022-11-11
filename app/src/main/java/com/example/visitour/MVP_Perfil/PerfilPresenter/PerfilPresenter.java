package com.example.visitour.MVP_Perfil.PerfilPresenter;

import com.example.visitour.Beans.Usuario;
import com.example.visitour.MVP_Perfil.PerfilModel.IPerfilModel;
import com.example.visitour.MVP_Perfil.PerfilModel.PerfilModel;
import com.example.visitour.MVP_Perfil.PerfilView.IPerfilView;

public class PerfilPresenter implements IPerfilPresenter{

    IPerfilModel perfilModel;
    IPerfilView perfilView;

    public PerfilPresenter(IPerfilView perfilView){
        this.perfilView = perfilView;
        this.perfilModel = new PerfilModel(this);
    }

    @Override
    public void getData(int id) {
        perfilModel.getData(id);
    }

    @Override
    public void saveData(int id, String nombre, String apellido, String correo, String nacionalidad, String telefono) {
        perfilModel.saveData(id,nombre,apellido,correo,nacionalidad,telefono);
    }

    @Override
    public void onDataSuccess(Usuario usuario) {
        perfilView.onDataSuccess(usuario);
    }

    @Override
    public void onDataFailure(String msg) {
        perfilView.onDataFailure(msg);
    }
}
