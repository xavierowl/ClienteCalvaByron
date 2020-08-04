package com.example.clientecalvabyron;

import java.time.LocalDate;

public class Movimiento {
	private int mov_id;

	private LocalDate mov_fecha;
	private String mov_descripcion;
	private int mov_ingreso;
	private int mov_egreso;
	private int saldo;
	private Producto producto;
	
	public Movimiento() {
	}

	public int getMov_id() {
		return mov_id;
	}

	public void setMov_id(int mov_id) {
		this.mov_id = mov_id;
	}

	public LocalDate getMov_fecha() {
		return mov_fecha;
	}

	public void setMov_fecha(LocalDate mov_fecha) {
		this.mov_fecha = mov_fecha;
	}

	public String getMov_descripcion() {
		return mov_descripcion;
	}

	public void setMov_descripcion(String mov_descripcion) {
		this.mov_descripcion = mov_descripcion;
	}

	public int getMov_ingreso() {
		return mov_ingreso;
	}

	public void setMov_ingreso(int mov_ingreso) {
		this.mov_ingreso = mov_ingreso;
	}

	public int getMov_egreso() {
		return mov_egreso;
	}

	public void setMov_egreso(int mov_egreso) {
		this.mov_egreso = mov_egreso;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Movimiento [mov_id=" + mov_id + ", mov_fecha=" + mov_fecha + ", mov_descripcion=" + mov_descripcion
				+ ", mov_ingreso=" + mov_ingreso + ", mov_egreso=" + mov_egreso + ", saldo=" + saldo + ", producto="
				+ producto + "]";
	}
}
