package recuperacionMarzoHibernate;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ClaveDetalle {
	
	@ManyToOne
	@JoinColumn(name="presupuesto", referencedColumnName = "codigo")
	
	private Presupuesto presupuesto;
	
	
	@ManyToOne
	@JoinColumn(name="Servicio", referencedColumnName = "codigo")
	
	private Servicio servicio;


	public ClaveDetalle() {
	}


	public ClaveDetalle(Presupuesto presupuesto, Servicio servicio) {
		this.presupuesto = presupuesto;
		this.servicio = servicio;
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
	
	
}
