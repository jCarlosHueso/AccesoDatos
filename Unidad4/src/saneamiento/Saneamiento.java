package saneamiento;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;

public class Saneamiento implements Serializable {
	
	@EmbeddedId	
private String id;
	@Column(nullable = false)
private Date fecha;
	@Column(nullable = false)
private Veterinario veterinario;
	public Saneamiento() {
	}	
	public Saneamiento(String id, Date fecha, Veterinario veterinario) {
		this.id = id;
		this.fecha = fecha;
		this.veterinario = veterinario;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Veterinario getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}



}
