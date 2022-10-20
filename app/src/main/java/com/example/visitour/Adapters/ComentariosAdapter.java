package com.example.visitour.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitour.Apis.ApiClient;
import com.example.visitour.Apis.ItemsApi;
import com.example.visitour.Beans.Comentario;
import com.example.visitour.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ViewHolder> {
    private List<Comentario> mComentarios;
    private Context context;
    SharedPreferences preferences;

    public ComentariosAdapter(List<Comentario> mComentarios) {
        this.mComentarios = mComentarios;
    }

    public void reloadData(List<Comentario> mComentarios) {
        this.mComentarios = mComentarios;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ComentariosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);

        LayoutInflater inflater = LayoutInflater.from(this.context);

        View contactView = inflater.inflate(R.layout.comentario_dinamico, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentariosAdapter.ViewHolder holder, int position) {
        Comentario comentario = mComentarios.get(position);

        TextView textViewUsuario = holder.mUsuario;
        textViewUsuario.setText(comentario.mUsuario);

        TextView textViewComentario = holder.mComentario;
        textViewComentario.setText(comentario.mComentario);

        ImageButton imageButtonBorrar = holder.mBorrar;

        if(comentario.mBorrar){
            imageButtonBorrar.setVisibility(View.VISIBLE);
            imageButtonBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemsApi mApi = ApiClient.getInstance().create(ItemsApi.class);
                    Call<String> itemCall = mApi.borrarComentario(comentario.id);
                    itemCall.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            mComentarios.remove(position);
                            reloadData(mComentarios);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            mComentarios.remove(position);
                            reloadData(mComentarios);
                        }
                    });
                }
            });
        } else {
            imageButtonBorrar.setVisibility(View.INVISIBLE);
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mUsuario;
        private TextView mComentario;
        private ImageButton mBorrar;

        public ViewHolder(@NonNull View comentarioView) {
            super(comentarioView);

            mUsuario = comentarioView.findViewById(R.id.comentario_usuario);
            mComentario = comentarioView.findViewById(R.id.comentario_comentario);
            mBorrar = comentarioView.findViewById(R.id.comentario_borrar);

            comentarioView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public int getItemCount() {
        return mComentarios.size();
    }
}
