package ejercicio1;

import java.io.Serializable;

public class ventas implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idProducto, cantidad;
	private double importe;

	public ventas() {
	}

	public ventas(int idProducto, int cantidad, double importe) {
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.importe = importe;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getImporte() {
		return importe;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public void mostrar() {
		System.out.println("id: " + idProducto + "\nVentas: " + cantidad + "\nimporte: " + importe);
	}
}
