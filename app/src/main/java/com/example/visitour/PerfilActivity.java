package com.example.visitour;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitour.Beans.Usuario;
import com.example.visitour.MVP_Perfil.PerfilPresenter.IPerfilPresenter;
import com.example.visitour.MVP_Perfil.PerfilPresenter.PerfilPresenter;
import com.example.visitour.MVP_Perfil.PerfilView.IPerfilView;
import com.example.visitour.databinding.ActivityPerfilBinding;

public class PerfilActivity extends AppCompatActivity implements IPerfilView {

    ActivityPerfilBinding binding;
    SharedPreferences sharedPreferences;
    IPerfilPresenter perfilPresenter;
    EditText nombre, apellido, nacionalidad, telefono, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        nombre = findViewById(R.id.perfil_nombre);
        apellido  = findViewById(R.id.perfil_apellido);
        nacionalidad  = findViewById(R.id.perfil_nacionalidad);
        telefono = findViewById(R.id.perfil_telefono);
        correo = findViewById(R.id.perfil_correo);

        perfilPresenter = new PerfilPresenter(this);
        perfilPresenter.getData(sharedPreferences.getInt("userId",-1));

        Button buttonCerrarSesion = findViewById(R.id.perfil_cerrar);
        buttonCerrarSesion.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("userId",-1);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
            startActivity(intent);
            finishAffinity();
        });

        Button buttonActualizarPerfil = findViewById(R.id.perfil_editar);
        buttonActualizarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilPresenter.saveData(sharedPreferences.getInt("userId",-1),
                        nombre.getText().toString(),
                        apellido.getText().toString(),
                        correo.getText().toString(),
                        nacionalidad.getText().toString(),
                        telefono.getText().toString());
            }
        });
    }

    @Override
    public void onDataSuccess(Usuario usuario) {
        nombre.setText(usuario.mNombre);
        apellido.setText(usuario.mApellido);
        nacionalidad.setText(usuario.mPais);
        telefono.setText(usuario.mTelefono);
        correo.setText(usuario.mCorreo);
    }

    @Override
    public void onDataFailure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}