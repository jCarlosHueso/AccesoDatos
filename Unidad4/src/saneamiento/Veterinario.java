package saneamiento;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Veterinario implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int colegiado;
	@Column(nullable = false)
private String nombre;
	public Veterinario() {
	}
	public Veterinario(int colegiado, String nombre) {
		this.colegiado = colegiado;
		this.nombre = nombre;
	}
	public int getColegiado() {
		return colegiado;
	}
	public void setColegiado(int colegiado) {
		this.colegiado = colegiado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

}
