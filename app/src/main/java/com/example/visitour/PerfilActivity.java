package com.example.visitour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.visitour.databinding.ActivityPerfilBinding;
import com.jakewharton.processphoenix.ProcessPhoenix;

public class PerfilActivity extends AppCompatActivity {

    ActivityPerfilBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        Button buttonCerrarSesion = findViewById(R.id.perfil_cerrar);
        buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("userId",-1);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}