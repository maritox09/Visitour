package com.example.visitour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.visitour.Adapters.ComentariosAdapter;
import com.example.visitour.Beans.Comentario;
import com.example.visitour.MVP_Comentarios.ComentariosPresenter.ComentariosPresenter;
import com.example.visitour.MVP_Comentarios.ComentariosPresenter.IComentariosPresenter;
import com.example.visitour.MVP_Comentarios.ComentariosView.IComentariosView;
import com.example.visitour.databinding.ActivityComentariosBinding;
import com.example.visitour.databinding.ActivityItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class ComentariosActivity extends AppCompatActivity implements IComentariosView {

    ActivityComentariosBinding binding;
    IComentariosPresenter comentariosPresenter;
    SharedPreferences preferences;
    private ComentariosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        binding = ActivityComentariosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();

        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        RecyclerView recyclerView = binding.comentariosRecyclerView;
        adapter = new ComentariosAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        comentariosPresenter = new ComentariosPresenter(this);
        comentariosPresenter.GetComentarios(bundle.getInt("id"),preferences.getInt("userId",0));

        EditText comentarioNuevo = binding.comentarioNuevo;
        ImageButton enviarComentario = binding.comentarioEnviar;

        enviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarComentario(bundle.getInt("id"),preferences.getInt("userId",0),comentarioNuevo.getText().toString());
            }
        });
    }

    @Override
    public void OnComentariosSuccess(List<Comentario> comentarios) {
        adapter.reloadData(comentarios);
    }

    @Override
    public void OnComentariosFailure(String msg) {
        Toast.makeText(this.getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void EnviarComentario(Integer id, Integer userId, String comentario) {
        comentariosPresenter.ComentarioNuevo(id,userId,comentario);
    }

    @Override
    public void ComentarioRegistrado() {
        Toast.makeText(this,"Gracias por tu comentario",Toast.LENGTH_SHORT).show();
        finish();
    }
}