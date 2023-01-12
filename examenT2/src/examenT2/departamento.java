package examenT2;

public class departamento {
int numero;
String nombre;
public departamento() {
	super();
}
public departamento(int numero, String nombre) {
	super();
	this.numero = numero;
	this.nombre = nombre;
}
public int getNumero() {
	return numero;
}
public void setNumero(int numero) {
	this.numero = numero;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
@Override
public String toString() {
	return "departamento [numero=" + numero + ", nombre=" + nombre + "]";
}


}
