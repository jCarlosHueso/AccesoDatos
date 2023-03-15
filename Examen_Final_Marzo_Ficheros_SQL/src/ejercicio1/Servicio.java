package ejercicio1;

public class Servicio {
private String codigo;
private String descripcion;
private float horaServicio;
private float horaEmpleado;
public Servicio() {
}
public Servicio(String codigo, String descripcion, float horaServicio, float horaEmpleado) {
	this.codigo = codigo;
	this.descripcion = descripcion;
	this.horaServicio = horaServicio;
	this.horaEmpleado = horaEmpleado;
}
public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public float getHoraServicio() {
	return horaServicio;
}
public void setHoraServicio(float horaServicio) {
	this.horaServicio = horaServicio;
}
public float getHoraEmpleado() {
	return horaEmpleado;
}
public void setHoraEmpleado(float horaEmpleado) {
	this.horaEmpleado = horaEmpleado;
}
@Override
public String toString() {
	return "Servicio [codigo=" + codigo + ", descripcion=" + descripcion + ", horaServicio=" + horaServicio
			+ ", horaEmpleado=" + horaEmpleado + "]";
}


}
