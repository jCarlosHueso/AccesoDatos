package saneamiento;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;

public class ResultadoSaneamiento implements Serializable {
	
	@EmbeddedId
	private Saneamiento saneamiento;
	@EmbeddedId
	private Animal animal;
	@Column(nullable = false)
	private boolean tuberculosis;
	@Column(nullable = false)
	private boolean brucelosis;
	@Column(nullable = false)
	private Date fechaResultado;
	public ResultadoSaneamiento() {
	}
	public ResultadoSaneamiento(Saneamiento saneamiento, Animal animal, boolean tuberculosis, boolean brucelosis,
			Date fechaResultado) {
		this.saneamiento = saneamiento;
		this.animal = animal;
		this.tuberculosis = tuberculosis;
		this.brucelosis = brucelosis;
		this.fechaResultado = fechaResultado;
	}
	public Saneamiento getSaneamiento() {
		return saneamiento;
	}
	public void setSaneamiento(Saneamiento saneamiento) {
		this.saneamiento = saneamiento;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public boolean isTuberculosis() {
		return tuberculosis;
	}
	public void setTuberculosis(boolean tuberculosis) {
		this.tuberculosis = tuberculosis;
	}
	public boolean isBrucelosis() {
		return brucelosis;
	}
	public void setBrucelosis(boolean brucelosis) {
		this.brucelosis = brucelosis;
	}
	public Date getFechaResultado() {
		return fechaResultado;
	}
	public void setFechaResultado(Date fechaResultado) {
		this.fechaResultado = fechaResultado;
	}

}
