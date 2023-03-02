package examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table
public class jugador implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Column(nullable = false)
	private String nombre;
	//un jugador para varios jugador partido
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "clave.jugador")
	List<jugador_partido> jugadorPartido=new ArrayList<>();
	
	public jugador() {
	}
	public jugador(int codigo, String nombre,List<jugador_partido> jugadorPartido) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.jugadorPartido=jugadorPartido;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<jugador_partido> getJugadorPartido() {
		return jugadorPartido;
	}
	public void setJugadorPartido(List<jugador_partido> jugadorPartido) {
		this.jugadorPartido = jugadorPartido;
	}
	@Override
	public String toString() {
		return "jugador [codigo=" + codigo + ", nombre=" + nombre + ", jugadorPartido=" + jugadorPartido.toString() + "]";
	}

	
	
}
