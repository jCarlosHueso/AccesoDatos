package Examen;

import java.util.ArrayList;
import java.util.Date;

public class Facturas {
private int numero;
private Date fecha;
private String cliente;
private ArrayList<Detalles> detalle;
private int facturaAnulacion;
public Facturas() {
}
public Facturas(int numero, Date fecha,	String cliente, ArrayList<Detalles> detalle, int facturaAnulacion) {
	this.numero = numero;
	this.fecha = fecha;
	this.cliente=cliente;
	this.detalle = detalle;
	this.facturaAnulacion = facturaAnulacion;
}
public int getNumero() {
	return numero;
}
public void setNumero(int numero) {
	this.numero = numero;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
public ArrayList<Detalles> getDetalle() {
	return detalle;
}
public void setDetalle(ArrayList<Detalles> detalle) {
	this.detalle = detalle;
}
public int getFacturaAnulacion() {
	return facturaAnulacion;
}
public void setFacturaAnulacion(int facturaAnulacion) {
	this.facturaAnulacion = facturaAnulacion;
}

public String getCliente() {
	return cliente;
}
public void setCliente(String cliente) {
	this.cliente = cliente;
}
@Override
public String toString() {
	return "Facturas [numero=" + numero + ", fecha=" + fecha + ", detalle=" + detalle + ", facturaAnulacion="
			+ facturaAnulacion + "]";
}


}

