package examen;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table
public class jugador_partido implements Serializable{
	
	@EmbeddedId
	private claveJ_P clave;
	@Column(nullable = false)
	private String resultado;
	public jugador_partido() {
	}
	public jugador_partido(claveJ_P clave, String resultado) {
		this.clave = clave;
		this.resultado = resultado;
	}
	public claveJ_P getClave() {
		return clave;
	}
	public void setClave(claveJ_P clave) {
		this.clave = clave;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	@Override
	public String toString() {
		return "jugador_partido [clave=" + clave.toString() + ", resultado=" + resultado + "]";
	}
	
}
