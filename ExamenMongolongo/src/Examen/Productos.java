package Examen;

public class Productos {
private String codigo;
private String nombre;
private float precio;
private int stock;
public Productos() {
	}
public Productos(String codigo, String nombre, float precio, int stock) {
	super();
	this.codigo = codigo;
	this.nombre = nombre;
	this.precio = precio;
	this.stock = stock;
}
public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public float getPrecio() {
	return precio;
}
public void setPrecio(float precio) {
	this.precio = precio;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
@Override
public String toString() {
	return "Productos [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + "]";
}


}
