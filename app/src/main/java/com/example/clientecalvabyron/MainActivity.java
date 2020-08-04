package com.example.clientecalvabyron;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button ingresar;
    private Button comprar;
    private Button vender;
    private Button listar;
    private Button kardex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ingresar = findViewById(R.id.btnIngresar);
        comprar = findViewById(R.id.btnComprar);
        vender = findViewById(R.id.btnVender);
        listar = findViewById(R.id.btnVerProductos);
        kardex = findViewById(R.id.btnVerKardex);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(MainActivity.this, Ingresar.class);
                startActivityForResult(ingresar, 1);
            }
        });

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent com = new Intent(MainActivity.this, Ingreso.class);
                startActivityForResult(com, 1);
            }
        });

        vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ven = new Intent(MainActivity.this, Egreso.class);
                startActivityForResult(ven, 1);
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listar = new Intent(MainActivity.this, listarProductos.class);
                startActivityForResult(listar, 1);
            }
        });

        kardex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listar = new Intent(MainActivity.this, ListarMovimientos.class);
                startActivityForResult(listar, 1);
            }
        });
    }
}