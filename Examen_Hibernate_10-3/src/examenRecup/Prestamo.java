package examenRecup;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "prestamo")
public class Prestamo {
	@EmbeddedId
	private ClavePrestamo idPrestamo;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDevolPrevista;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date fechaDevolReal;
	public Prestamo() {
	}
	public Prestamo(ClavePrestamo idPrestamo, Date fechaDevolPrevista, Date fechaDevolReal) {
		this.idPrestamo = idPrestamo;
		this.fechaDevolPrevista = fechaDevolPrevista;
		this.fechaDevolReal = fechaDevolReal;
	}
	
	public ClavePrestamo getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(ClavePrestamo idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Date getFechaDevolPrevista() {
		return fechaDevolPrevista;
	}
	public void setFechaDevolPrevista(Date fechaDevolPrevista) {
		this.fechaDevolPrevista = fechaDevolPrevista;
	}
	public Date getFechaDevolReal() {
		return fechaDevolReal;
	}
	public void setFechaDevolReal(Date fechaDevolReal) {
		this.fechaDevolReal = fechaDevolReal;
	}
	
	
	
}
