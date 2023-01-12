package examen;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement
@XmlType(propOrder={"sucursal","movimiento"})
public class movimientos {
private String sucursal;
private ArrayList<movimiento> movimiento=null;
public movimientos() {
}
public movimientos(String sucursal, ArrayList<examen.movimiento> movimiento) {
	super();
	this.sucursal = sucursal;
	this.movimiento = movimiento;
}
@XmlElement
public String getSucursal() {
	return sucursal;
}
public void setSucursal(String suculsal) {
	this.sucursal = suculsal;
}
@XmlElementWrapper (name="movimiento")
public ArrayList<movimiento> getMovimiento() {
	return movimiento;
}
public void setMovimiento(ArrayList<movimiento> movimiento) {
	this.movimiento = movimiento;
}



	
}
