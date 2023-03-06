package Examen;

public class Detalles {
	
private Productos producto;
private int cantidad;
private float precioUnidad;


public Detalles() {
	
}
public Detalles(Productos producto, int cantidad, float precioUnidad) {
	super();
	this.producto = producto;
	this.cantidad = cantidad;
	this.precioUnidad = precioUnidad;
}
public Productos getProducto() {
	return producto;
}
public void setProducto(Productos producto) {
	this.producto = producto;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public float getPrecioUnidad() {
	return precioUnidad;
}
public void setPrecioUnidad(float precioUnidad) {
	this.precioUnidad = precioUnidad;
}
@Override
public String toString() {
	return "Detalles [producto=" + producto + ", cantidad=" + cantidad + ", precioUnidad=" + precioUnidad + "]";
}




}
