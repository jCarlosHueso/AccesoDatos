package taller;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class PiezaReparacion implements  Serializable{
	@EmbeddedId
	private clavePR clave;
	@Column(nullable = false)
	private float importe;
	@Column(nullable = false)
	private int cantidad;
	
	public PiezaReparacion() {
		
	}

	public PiezaReparacion(clavePR clave, float importe, int cantidad) {
		this.clave = clave;
		this.importe = importe;
		this.cantidad = cantidad;
	}
	
	public void mostrar() {
		System.out.println("Reparacion: " +clave.getReparacion().getId()
							+"\tPieza: " +clave.getPieza().getCodigo()+
							          "-"+clave.getPieza().getDescripcion()
							+"\tImporte: " +importe
							+"\tCantidad. " +cantidad);
	}

	

	public clavePR getClave() {
		return clave;
	}

	public void setClave(clavePR clave) {
		this.clave = clave;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
