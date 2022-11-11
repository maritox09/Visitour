package com.example.visitour.Apis;

import com.example.visitour.Beans.Comentario;
import com.example.visitour.Beans.Item;
import com.example.visitour.Beans.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ItemsApi {
    @FormUrlEncoded
    @POST("/lugares.php")
    Call<List<Item>> getLugares(@Field("userId") Integer id);

    @FormUrlEncoded
    @POST("/restaurantes.php")
    Call<List<Item>> getRestaurantes(@Field("userId") Integer id);

    @FormUrlEncoded
    @POST("/favoritos.php")
    Call<List<Item>> getFavoritos(@Field("userId") Integer id);

    @FormUrlEncoded
    @POST("/detalles.php")
    Call<Item> getDetalles(@Field("id") Integer id, @Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("/fav.php")
    Call<String> favItem(@Field("id") Integer id, @Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("/unfav.php")
    Call<String> unfavItem(@Field("id") Integer id, @Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("/comentarios.php")
    Call<List<Comentario>> getComentarios(@Field("id") Integer id,@Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("/nuevocomentario.php")
    Call<String> comentarioNuevo(@Field("id") Integer id,@Field("userId") Integer userId,@Field("comentario") String comentario);

    @FormUrlEncoded
    @POST("/borrarcomentario.php")
    Call<String> borrarComentario(@Field("id") Integer id);

    @FormUrlEncoded
    @POST("/registrarrating.php")
    Call<String> registrarRating(@Field("id") Integer id,@Field("userId") Integer userId,@Field("rating") Float rating);

    @FormUrlEncoded
    @POST("/perfil.php")
    Call<Usuario> getPerfil(@Field("id") Integer id);

    @FormUrlEncoded
    @POST("/actualizarperfil.php")
    Call<String> savePerfil(@Field("id") Integer id, @Field("nombre") String nombre, @Field("apellido") String apellido, @Field("correo") String correo,@Field("nacionalidad") String nacionalidad,@Field("telefono") String telefono);

    @FormUrlEncoded
    @POST("/nearme.php")
    Call<List<Item>> getNear(@Field("id") Integer id, @Field("longitud") float longitud, @Field("latitud") float latitud);
}
