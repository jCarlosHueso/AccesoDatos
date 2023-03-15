package ejercicio1;

public class Detalle_Presupuesto {
private Presupuesto presupuesto;
private Servicio servicio;
private String descripcion;
private int horas;
private float importe;

public Detalle_Presupuesto() {
}

public Detalle_Presupuesto(Presupuesto presupuesto, Servicio servicio, String descripcion, int horas, float importe) {
	this.presupuesto = presupuesto;
	this.servicio = servicio;
	this.descripcion = descripcion;
	this.horas = horas;
	this.importe = importe;
}

public Presupuesto getPresupuesto() {
	return presupuesto;
}
public void setPresupuesto(Presupuesto presupuesto) {
	this.presupuesto = presupuesto;
}
public Servicio getServicio() {
	return servicio;
}
public void setServicio(Servicio servicio) {
	this.servicio = servicio;
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

@Override
public String toString() {
	return "Detalle_Presupuesto [presupuesto=" + presupuesto + ", servicio=" + servicio + ", descripcion=" + descripcion
			+ ", horas=" + horas + ", importe=" + importe + "]";
}
		

}
