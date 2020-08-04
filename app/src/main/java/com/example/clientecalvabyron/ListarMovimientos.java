package com.example.clientecalvabyron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarMovimientos extends AppCompatActivity implements AdapterMovimiento.OnMovimientoListener{
    private Button obtener;
    private EditText codigo;
    private APIService apiService;
    private AdapterMovimiento adaptador;
    private RecyclerView rvMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_movimientos);
        obtener = findViewById(R.id.btnObtener);
        codigo = findViewById(R.id.etCodigo);
        adaptador = new AdapterMovimiento(this);
        rvMovimientos = findViewById(R.id.rvMovimientos);
        rvMovimientos.setLayoutManager(new LinearLayoutManager(this));

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

        obtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Movimiento>> movimientosRequest = apiService.getMovimientos(Integer.valueOf(codigo.getText().toString()));

                movimientosRequest.enqueue(new Callback<List<Movimiento>>() {
                    @Override
                    public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                        adaptador.limpiar();
                        //Snackbar.make(findViewById(R.id.containerInicio), "Lllega al servicio", Snackbar.LENGTH_LONG).show();
                        for(int x = 0; x < response.body().size(); x++){
                            Movimiento movimiento = new Movimiento();
                            movimiento.setMov_id(response.body().get(x).getMov_id());
                            movimiento.setMov_fecha(response.body().get(x).getMov_fecha());
                            movimiento.setMov_descripcion(response.body().get(x).getMov_descripcion());
                            movimiento.setMov_ingreso(response.body().get(x).getMov_ingreso());
                            movimiento.setMov_egreso(response.body().get(x).getMov_egreso());
                            movimiento.setSaldo(response.body().get(x).getSaldo());
                            adaptador.addMovimiento(movimiento);
                        }
                        rvMovimientos.setAdapter(adaptador);
                        System.out.println("Tamaño: "+adaptador.getItemCount());
                    }

                    @Override
                    public void onFailure(Call<List<Movimiento>> call, Throwable t) {
                        System.out.println("Se ah producido el siguiente error: "+t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public void onMovimientoClick(int Movimiento) {

    }
}