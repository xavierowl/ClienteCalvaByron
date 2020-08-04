package com.example.clientecalvabyron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listarProductos extends AppCompatActivity implements AdapterProducto.OnProductoListener{
    private APIService apiService;
    private AdapterProducto adaptador;
    private RecyclerView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos);
        adaptador = new AdapterProducto(this);
        listado = findViewById(R.id.rvProductos);
        listado.setLayoutManager(new LinearLayoutManager(this));
        setAdapterCreditos(listado);
    }

    public void setAdapterCreditos(RecyclerView rvCreditos){
        //Se establece el escenario para realizar las peticiones web
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/ServidorCalvaByron/srv/cliente/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
        Call<List<Producto>> productosRequest = apiService.getProductos();

        productosRequest.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                adaptador.limpiar();
                //Snackbar.make(findViewById(R.id.containerInicio), "Lllega al servicio", Snackbar.LENGTH_LONG).show();
                for(int x = 0; x < response.body().size(); x++){
                    Producto producto = new Producto();
                    producto.setPro_nombre(response.body().get(x).getPro_nombre());
                    producto.setPro_stock(response.body().get(x).getPro_stock());
                    adaptador.addProducto(producto);
                }
                rvCreditos.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                System.out.println("Se ah producido el siguiente error: "+t.getMessage());
            }
        });
    }

    @Override
    public void onProductoClick(int Producto) {

    }
}