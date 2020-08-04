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

public class AdapterMovimiento extends RecyclerView.Adapter<AdapterMovimiento.ViewHolder>{
    private List<Movimiento> movimientos;
    private Context context;
    private OnMovimientoListener onMovimientoListener;

    public AdapterMovimiento(OnMovimientoListener onMovimientoListener){
        movimientos = new ArrayList<Movimiento>();
        this.onMovimientoListener = onMovimientoListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimiento, parent,
                false);
        context = parent.getContext();
        return new ViewHolder(view, onMovimientoListener);
    }

    public void setmovimientos(List<Movimiento> movimientos){
        this.movimientos = movimientos;
    };

    public List<Movimiento> getmovimientos(){
        return movimientos;
    };

    public void addMovimiento(Movimiento Movimiento){
        movimientos.add(Movimiento);
    }

    public void limpiar(){
        movimientos.removeAll(movimientos);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovimiento.ViewHolder holder, int position) {
        holder.tvCodigo.setText(String.valueOf(movimientos.get(position).getMov_id()));
        holder.tvFecha.setText(movimientos.get(position).getMov_fecha());
        holder.tvTipo.setText(movimientos.get(position).getMov_descripcion());
        holder.tvIngreso.setText(String.valueOf(movimientos.get(position).getMov_ingreso()));
        holder.tvEgreso.setText(String.valueOf(movimientos.get(position).getMov_egreso()));
        holder.tvSaldo.setText(String.valueOf(movimientos.get(position).getSaldo()));
    }

    @Override
    public int getItemCount() {
        return this.movimientos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvCodigo;
        TextView tvFecha;
        TextView tvTipo;
        TextView tvIngreso;
        TextView tvEgreso;
        TextView tvSaldo;
        OnMovimientoListener onMovimientoListener;

        ViewHolder(@NonNull View itemView, OnMovimientoListener onMovimientoListener){
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvIngreso = itemView.findViewById(R.id.tvIngreso);
            tvEgreso = itemView.findViewById(R.id.tvEgreso);
            tvSaldo = itemView.findViewById(R.id.tvSaldo);

            this.onMovimientoListener = onMovimientoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMovimientoListener.onMovimientoClick(getAdapterPosition());
        }
    }

    public interface OnMovimientoListener{
        void onMovimientoClick(int Movimiento);
    }
}
