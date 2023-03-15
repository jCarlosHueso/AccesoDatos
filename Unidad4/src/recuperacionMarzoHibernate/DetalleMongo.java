package recuperacionMarzoHibernate;

public class DetalleMongo {
private String descripcion;
private int horas;
private float importe;
public DetalleMongo() {
}
public DetalleMongo(String descripcion, int horas, float importe) {
	this.descripcion = descripcion;
	this.horas = horas;
	this.importe = importe;
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
	return "DetalleMongo [descripcion=" + descripcion + ", horas=" + horas + ", importe=" + importe + "]";
}


}
