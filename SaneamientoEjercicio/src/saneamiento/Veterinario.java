package saneamiento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Veterinario implements Serializable{
	@Id
	private int colegiado;
	@Column(nullable = false)
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "veterinario")
	List<Saneamiento>saneamiento = new ArrayList<>();
	
	public Veterinario() {
		
	}

	public Veterinario(int colegiado, String nombre, List<Saneamiento> saneamiento) {
		this.colegiado = colegiado;
		this.nombre = nombre;
		this.saneamiento = saneamiento;
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

	public List<Saneamiento> getSaneamiento() {
		return saneamiento;
	}

	public void setSaneamiento(List<Saneamiento> saneamiento) {
		this.saneamiento = saneamiento;
	}
	
	public void mostrar(boolean mostrarSaneamiento) {
		System.out.println("Colegiado: "+colegiado+
				"\tNombre: "+nombre);
		if(mostrarSaneamiento) {
			for(Saneamiento s:saneamiento) {
				s.mostrar(false);
			}	
		}
	}
}
