package com.example.clientecalvabyron;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Ingresar extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtStock;
    private EditText txtPrecio;
    private Button ingresarFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        txtNombre = findViewById(R.id.txtNombre);
        txtStock = findViewById(R.id.txtStock);
        txtPrecio = findViewById(R.id.txtPrecio);
        ingresarFinal = findViewById(R.id.ingresarFinal);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/ServidorCalvaByron/srv/cliente/")
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);

        ingresarFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto = new Producto();
                producto.setPro_nombre(txtNombre.getText().toString());
                producto.setPro_precio(Double.valueOf(txtPrecio.getText().toString()));
                producto.setPro_stock(Integer.valueOf(txtStock.getText().toString()));
                System.out.println(producto.toString());
                Call<ResponseBody> result = apiService.ingresarProducto(txtNombre.getText().toString(),
                        Integer.valueOf(txtStock.getText().toString()),
                        Double.valueOf(txtPrecio.getText().toString()));
                result.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String respuesta = response.body().string();
                            if(respuesta.equals("exito")){
                                System.out.println("Se ah ingresado el producto exitosamente");
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