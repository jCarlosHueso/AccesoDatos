package monglo;

public class Destinatarios {
private String empleado;
private boolean leido;
public Destinatarios() {
}
public Destinatarios(String empleado, boolean leido) {
	this.empleado = empleado;
	this.leido = leido;
}
public String getEmpleado() {
	return empleado;
}
public void setEmpleado(String empleado) {
	this.empleado = empleado;
}
public boolean isLeido() {
	return leido;
}
public void setLeido(boolean leido) {
	this.leido = leido;
}
@Override
public String toString() {
	return "Destinatarios [empleado=" + empleado + ", leido=" + leido + "]";
}


}
