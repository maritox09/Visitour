package com.example.visitour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitour.databinding.ActivityMainBinding;
import com.example.visitour.registro.activity_Registro;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        EditText textView_email = mainBinding.usuario;
        EditText textView_pass = mainBinding.pass;

        Button button_IniciarSesion = mainBinding.iniciarSesion;
        Button button_Registrarse = mainBinding.registrarse;

        button_Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_registro = new Intent(getApplicationContext(),activity_Registro.class);
                startActivity(intent_registro);
            }
        });
    }
}