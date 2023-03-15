package ejercicio1;

public class Cliente {
private String nif;
private String nombre;
private String telefono;
public Cliente() {

}
public Cliente(String nif, String nombre, String telefono) {
	this.nif = nif;
	this.nombre = nombre;
	this.telefono = telefono;
}
public String getNif() {
	return nif;
}
public void setNif(String nif) {
	this.nif = nif;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
@Override
public String toString() {
	return "Cliente [nif=" + nif + ", nombre=" + nombre + ", telefono=" + telefono + "]";
}


}
