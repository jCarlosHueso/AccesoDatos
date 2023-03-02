package examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table
public class partido implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(nullable = false)
	private int numSets;
	@ManyToOne
	@JoinColumn(name = "jugador",referencedColumnName = "codigo")
	@Column(nullable = false)
	private jugador ganador;
	//un partido a muchos jugador_partido
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "clave.partido")
	List<jugador_partido> jugadorPartido=new ArrayList<>();
	public partido() {
	}
	public partido(int codigo, Date fecha, int numSets, jugador ganador,List<jugador_partido>jugadorpartido) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.numSets = numSets;
		this.ganador = ganador;
		this.jugadorPartido=jugadorpartido;
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
	public int getNumSets() {
		return numSets;
	}
	public void setNumSets(int numSets) {
		this.numSets = numSets;
	}
	public jugador getGanador() {
		return ganador;
	}
	public void setGanador(jugador ganador) {
		this.ganador = ganador;
	}
	
	
	public List<jugador_partido> getJugadorPartido() {
		return jugadorPartido;
	}
	public void setJugadorPartido(List<jugador_partido> jugadorPartido) {
		this.jugadorPartido = jugadorPartido;
	}
	@Override
	public String toString() {
		return "partido [codigo=" + codigo + ", fecha=" + fecha + ", numSets=" 
	+ numSets + ", ganador=" + ganador.getNombre()
				+ ", jugadorPartido=" + jugadorPartido.toString() + "]";
	}
	
}
