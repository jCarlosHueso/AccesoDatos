package recuperacionMarzoHibernate;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Entity
public class Servicio {
	@Id
private String codigo;
	@Column(nullable = false)

private String descripcion;
	@Column(nullable = false)

private float horaServicio;
	@Column(nullable = false)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="clave.servicio")
	List<Detalle_Presupuesto> detalles= new ArrayList<Detalle_Presupuesto>();

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
