package saneamiento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class ResultadoSaneamiento implements Serializable{
	@EmbeddedId
	private claveAS clave;
	@Column(nullable = false)
	private boolean tuberculosis;
	@Column(nullable = false)
	private boolean brucelosis;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaResultado;
	
	public ResultadoSaneamiento() {
		
	}

	public ResultadoSaneamiento(claveAS clave, boolean tuberculosis, boolean brucelosis,
			Date fechaResultado) {
		this.clave = clave;
		this.tuberculosis = tuberculosis;
		this.brucelosis = brucelosis;
		this.fechaResultado = fechaResultado;
	}
	
	public claveAS getClave() {
		return clave;
	}

	public void setClave(claveAS clave) {
		this.clave = clave;
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
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Animal: "+clave.getAnimal().getNombre()+
				"\tSaneamiento: "+clave.getSanea().getId()+
				"\tTubercolosis: "+tuberculosis+
				"\tBrucelosis: "+brucelosis+
				"FechaResultado: "+formato.format(fechaResultado));
	}
}
