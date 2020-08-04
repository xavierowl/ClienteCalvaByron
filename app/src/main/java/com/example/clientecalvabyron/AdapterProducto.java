package com.example.clientecalvabyron;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ViewHolder>{
    private List<Producto> productos;
    private Context context;
    private OnProductoListener onProductoListener;

    public AdapterProducto(OnProductoListener onProductoListener){
        productos = new ArrayList<Producto>();
        this.onProductoListener = onProductoListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos, parent,
                false);
        context = parent.getContext();
        return new ViewHolder(view, onProductoListener);
    }

    public void setproductos(List<Producto> productos){
        this.productos = productos;
    };

    public List<Producto> getproductos(){
        return productos;
    };

    public void addProducto(Producto Producto){
        productos.add(Producto);
    }

    public void limpiar(){
        productos.removeAll(productos);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProducto.ViewHolder holder, int position) {
        holder.tvProducto.setText("Producto: "+productos.get(position).getPro_nombre());
        holder.tvStock.setText("Stock: "+String.valueOf(productos.get(position).getPro_stock()));
    }

    @Override
    public int getItemCount() {
        return this.productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvProducto;
        TextView tvStock;
        OnProductoListener onProductoListener;

        ViewHolder(@NonNull View itemView, OnProductoListener onProductoListener){
            super(itemView);
            tvProducto = itemView.findViewById(R.id.tvProducto);
            tvStock = itemView.findViewById(R.id.tvStock);

            this.onProductoListener = onProductoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onProductoListener.onProductoClick(getAdapterPosition());
        }
    }

    public interface OnProductoListener{
        void onProductoClick(int Producto);
    }
}
