package com.example.clientecalvabyron;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Egreso extends AppCompatActivity {
    private EditText txtId;
    private EditText txtCantidad;
    private Button btnVender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egreso);

        txtId = findViewById(R.id.txtId);
        txtCantidad = findViewById(R.id.txtCantidad);
        btnVender = findViewById(R.id.btnVender);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/ServidorCalvaByron/srv/cliente/")
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);

        btnVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.valueOf(txtId.getText().toString());
                int cantidad = Integer.valueOf(txtCantidad.getText().toString());
                System.out.println("Info: "+id+", "+cantidad);
                Call<ResponseBody> result = apiService.setEgreso(id, cantidad);
                result.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String respuesta = response.body().string();
                            if(respuesta.equals("exito")){
                                System.out.println("Se ah realizado el egreso exitosamente");
                                finish();
                            }
                        } catch (Exception e) {
                            System.out.println("Se ah producido un error. ("+e.getMessage());
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println("Se ah producido un erro al solicitar el servicio ("+t.getMessage()+")");
                    }
                });
            }
        });
    }
}