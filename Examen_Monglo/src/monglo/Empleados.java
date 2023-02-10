package monglo;

public class Empleados {
private String dni;
private String nombre;
private String departamento;
public Empleados() {
}
public Empleados(String dni, String nombre, String departamento) {
	this.dni = dni;
	this.nombre = nombre;
	this.departamento = departamento;
}
public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getDepartamento() {
	return departamento;
}
public void setDepartamento(String departamento) {
	this.departamento = departamento;
}

@Override
public String toString() {
	return "Empleado [dni=" + dni + ", nombre=" + nombre + ", departamento=" + departamento + "]";
}

}
