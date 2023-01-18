package examen;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"sucursal","movimientos"})
public class Movimientos {
	private String sucursal;
	private ArrayList<Movimiento> movimientos = new ArrayList();
	
	
	public Movimientos(String sucursal, ArrayList<Movimiento> movimientos) {
		super();
		this.sucursal = sucursal;
		this.movimientos = movimientos;
	}
	public Movimientos() {
		super();
	}
	
	@XmlElement
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	@XmlElementWrapper
	@XmlElement(name="movimiento")
	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(ArrayList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	

}
