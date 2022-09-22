package com.example.visitour.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.visitour.MainActivity;
import com.example.visitour.R;

import java.util.HashMap;
import java.util.Map;

public class activity_Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditText email = findViewById(R.id.email);
        EditText nombre = findViewById(R.id.nombre);
        EditText apellido = findViewById(R.id.apellido);
        EditText nacionalidad = findViewById(R.id.nacionalidad);
        EditText pass = findViewById(R.id.pass);
        EditText vpass = findViewById(R.id.vpass);
        EditText telefono = findViewById(R.id.telefono);

        Button botton_registrarse = findViewById(R.id.botton_registrarse);

        AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        mAwesomeValidation.addValidation(vpass, pass, getString(R.string.toast_pass_no_coincide));
        mAwesomeValidation.addValidation(email, Patterns.EMAIL_ADDRESS,getString(R.string.validation_correo_valido));
        mAwesomeValidation.addValidation(nombre, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Cambo obligatorio");
        mAwesomeValidation.addValidation(apellido, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Cambo obligatorio");
        mAwesomeValidation.addValidation(nacionalidad, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Cambo obligatorio");
        mAwesomeValidation.addValidation(telefono, "^[0-9\\s]{1,}[\\.]{0,1}[0-9\\s]{0,}$", "Igrese un telefono valido");


        botton_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAwesomeValidation.validate()){
                    String mNombre = nombre.getText().toString();
                    String mApellido = apellido.getText().toString();
                    String mEmail = email.getText().toString();
                    String mPass = pass.getText().toString();
                    String mNacionalidad = nacionalidad.getText().toString();
                    String mTelefono = telefono.getText().toString();
                    String url = "https://hola.falcorp.net/registro.php";


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(activity_Registro.this, getText(R.string.toast_registro_satisfactorio), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(activity_Registro.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(activity_Registro.this, getText(R.string.toast_ErrorInterno),Toast.LENGTH_SHORT).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams(){
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("nombre",mNombre);
                            params.put("apellido",mApellido);
                            params.put("correo",mEmail);
                            params.put("nacionalidad",mNacionalidad);
                            params.put("telefono",mTelefono);
                            params.put("password",mPass);

                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(activity_Registro.this);
                    requestQueue.add(stringRequest);
                }
            }
        });
    }
}