package recuperacionMarzoHibernate;

import java.util.ArrayList;
import java.util.Date;

public class PresupuestoMongo {
private int codigo;
private Date fecha;
private ArrayList<DetalleMongo>detalles;

public PresupuestoMongo() {
}

public PresupuestoMongo(int codigo, Date fecha, ArrayList<DetalleMongo> detalles) {
	this.codigo = codigo;
	this.fecha = fecha;
	this.detalles = detalles;
}

public int getCodigo() {
	return codigo;
}

public void setCodigo(int codigo) {
	this.codigo = codigo;
}

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

public ArrayList<DetalleMongo> getDetalles() {
	return detalles;
}

public void setDetalles(ArrayList<DetalleMongo> detalles) {
	this.detalles = detalles;
}

@Override
public String toString() {
	return "presupuestoMongo [codigo=" + codigo + ", fecha=" + fecha + ", detalles=" + detalles + "]";
}



}
