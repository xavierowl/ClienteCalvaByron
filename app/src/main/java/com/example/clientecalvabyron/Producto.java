package com.example.clientecalvabyron;

import java.util.List;

public class Producto {
	private int pro_id;

	private String pro_nombre;

	private int pro_stock;

	private double pro_precio;

	private List<Movimiento> pro_movimientos;

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public String getPro_nombre() {
		return pro_nombre;
	}

	public void setPro_nombre(String pro_nombre) {
		this.pro_nombre = pro_nombre;
	}

	public int getPro_stock() {
		return pro_stock;
	}

	public void setPro_stock(int pro_stock) {
		this.pro_stock = pro_stock;
	}

	public double getPro_precio() {
		return pro_precio;
	}

	public void setPro_precio(double pro_precio) {
		this.pro_precio = pro_precio;
	}

	public List<Movimiento> getPro_movimientos() {
		return pro_movimientos;
	}

	public void setPro_movimientos(List<Movimiento> pro_movimientos) {
		this.pro_movimientos = pro_movimientos;
	}

	@Override
	public String toString() {
		return "Producto [pro_id=" + pro_id + ", pro_nombre=" + pro_nombre + ", pro_stock=" + pro_stock
				+ ", pro_precio=" + pro_precio + ", pro_movimientos=" + pro_movimientos + "]";
	}
}
