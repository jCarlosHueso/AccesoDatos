package examen;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType (propOrder={"cuenta","nombre","apellidos","importe"})
public class movimiento {
String tipo;
int cuenta;
String nombre,apellido;
float importe;
public movimiento() {
	super();
}
public movimiento(String tipo, int cuenta, String nombre, String apellido, float importe) {
	super();
	this.tipo = tipo;
	this.cuenta = cuenta;
	this.nombre = nombre;
	this.apellido = apellido;
	this.importe = importe;
}
@XmlAttribute
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
@XmlElement
public int getCuenta() {
	return cuenta;
}
public void setCuenta(int cuenta) {
	this.cuenta = cuenta;
}@XmlElement
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}@XmlElement
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}@XmlElement
public float getImporte() {
	return importe;
}
public void setImporte(float importe) {
	this.importe = importe;
}

}
