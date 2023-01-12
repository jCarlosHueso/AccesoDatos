package examen19_12;

public class FacturaDetalle {

	private int factura;
	private int servicio;
	private float precio;
	
	
	public FacturaDetalle() {
		super();
	}


	public FacturaDetalle(int factura, int servicio, float precio) {
		super();
		this.factura = factura;
		this.servicio = servicio;
		this.precio = precio;
	}


	public int getFactura() {
		return factura;
	}


	public void setFactura(int factura) {
		this.factura = factura;
	}


	public int getServicio() {
		return servicio;
	}


	public void setServicio(int servicio) {
		this.servicio = servicio;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "FacturaDetalle [factura=" + factura + ", servicio=" + servicio + ", precio=" + precio + "]";
	}
	
	
}
