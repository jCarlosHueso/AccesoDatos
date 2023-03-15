package recuperacionMarzoHibernate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Detalle_Presupuesto {
	
@EmbeddedId
private ClaveDetalle clave;
@Column(nullable = false)

private String descripcion;
@Column(nullable = false)

private int horas;
@Column(nullable = false)

private float importe;


public Detalle_Presupuesto() {
}

public Detalle_Presupuesto(ClaveDetalle clave, String descripcion, int horas, float importe) {
	super();
	this.clave = clave;
	this.descripcion = descripcion;
	this.horas = horas;
	this.importe = importe;
}


public ClaveDetalle getClave() {
	return clave;
}

public void setClave(ClaveDetalle clave) {
	this.clave = clave;
}

public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public int getHoras() {
	return horas;
}
public void setHoras(int horas) {
	this.horas = horas;
}
public float getImporte() {
	return importe;
}
public void setImporte(float importe) {
	this.importe = importe;
}




		

}
