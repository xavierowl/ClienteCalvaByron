package com.example.clientecalvabyron;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @GET("ingresarProducto")
    Call<ResponseBody> ingresarProducto(@Query("nombre") String nombre,
                                        @Query("stock") int stock,
                                        @Query("precio") double precio);

    @GET("setIngreso")
    Call<ResponseBody> setIngreso(@Query("pro_id") int pro_id,
                                        @Query("pro_cantidad") int pro_cantidad);

    @GET("setEgreso")
    Call<ResponseBody> setEgreso(@Query("pro_id") int pro_id,
                                  @Query("pro_cantidad") int pro_cantidad);
}