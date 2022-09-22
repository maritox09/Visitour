package com.example.visitour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.visitour.databinding.ActivityMainBinding;
import com.example.visitour.registro.activity_Registro;
import com.example.visitour.registro.pags.LugaresActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

        button_IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = textView_email.getText().toString();
                String mPass = textView_pass.getText().toString();
                String url = "https://hola.falcorp.net/login.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                JSONObject jsonRespone = null;
                                String mId = null;

                                try {
                                    jsonRespone = new JSONObject(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    mId = jsonRespone.getString("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                if (mId == "null") {
                                    Toast.makeText(MainActivity.this, getText(R.string.toast_login_incorrecto), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, getText(R.string.toast_login_exitoso), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LugaresActivity.class);
                                    intent.putExtra("id", Integer.parseInt(mId));
                                    startActivity(intent);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, getText(R.string.toast_ErrorInterno),Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("correo",mEmail);
                        params.put("password",mPass);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}