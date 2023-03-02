package examen;

import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class claveJ_P implements Serializable{
	@ManyToOne
	@JoinColumn(name = "partido",referencedColumnName = "codigo")
	private partido partido;
	@ManyToOne
	@JoinColumn(name = "jugador",referencedColumnName = "codigo")
	private jugador jugador;
	
	public claveJ_P() {
	}
	public claveJ_P(partido partido, jugador jugador) {
		this.partido = partido;
		this.jugador = jugador;
	}
	public partido getPartido() {
		return partido;
	}
	public void setPartido(partido partido) {
		this.partido = partido;
	}
	public jugador getJugador() {
		return jugador;
	}
	public void setJugador(jugador jugador) {
		this.jugador = jugador;
	}
	@Override
	public String toString() {
		return "claveJ_P [partido=" + partido + ", jugador=" + jugador + "]";
	}
	
}
