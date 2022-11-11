package com.example.visitour.MVP_Perfil.PerfilView;

import com.example.visitour.Beans.Usuario;

public interface IPerfilView {
    void onDataSuccess(Usuario usuario);
    void onDataFailure(String msg);
}
