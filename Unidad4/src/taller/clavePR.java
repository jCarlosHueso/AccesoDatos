package taller;

import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class clavePR implements Serializable{
	@ManyToOne
	@JoinColumn(name = "reparacion",referencedColumnName = "id")
	//¿Qué campo de reparación contiene la PK?
	private Reparacion reparacion;
	@ManyToOne
	@JoinColumn(name = "pieza",referencedColumnName = "codigo")
	private Pieza pieza;
	
	
	public clavePR(Reparacion reparacion, Pieza pieza) {
		this.reparacion = reparacion;
		this.pieza = pieza;
	}
	public clavePR() {
	}
	public Reparacion getReparacion() {
		return reparacion;
	}
	public void setReparacion(Reparacion reparacion) {
		this.reparacion = reparacion;
	}
	public Pieza getPieza() {
		return pieza;
	}
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
	
}
