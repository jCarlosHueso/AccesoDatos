package ejercicio1;

public class Fichero {
private String servicio;
private Long horas;
private float total;
private boolean baja;
public Fichero() {
}
public Fichero(String servicio, Long horas, float total,boolean baja) {
	this.servicio = servicio;
	this.horas = horas;
	this.total = total;
	this.baja=baja;
}
public String getServicio() {
	return servicio;
}
public void setServicio(String servicio) {
	this.servicio = servicio;
}
public Long getHoras() {
	return horas;
}
public void setHoras(Long horas) {
	this.horas = horas;
}
public float getTotal() {
	return total;
}
public void setTotal(float total) {
	this.total = total;
}
@Override
public String toString() {
	return "Fichero [servicio=" + servicio + ", horas=" + horas + ", total=" + total + " baja= "+baja+ "]";
}
public boolean isBaja() {
	return baja;
}
public void setBaja(boolean baja) {
	this.baja = baja;
}


}
