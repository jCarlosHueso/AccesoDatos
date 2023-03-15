package examenRecup;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


public class ClavePrestamo {
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
private Date fechaP;
	
	@ManyToOne
	@JoinColumn(name="socio", referencedColumnName = "id")
private Socio socio;
	
	@ManyToOne
	@JoinColumn(name="libro", referencedColumnName = "isbn")
	
private Libro libro;
	
	
	
	
public ClavePrestamo() {
}
public ClavePrestamo(Date fechaP, Socio socio, Libro libro) {
	this.fechaP = fechaP;
	this.socio = socio;
	this.libro = libro;
}
public Date getfechaP() {
	return fechaP;
}
public void setfechaP(Date fechaP) {
	this.fechaP = fechaP;
}
public Socio getSocio() {
	return socio;
}
public void setSocio(Socio socio) {
	this.socio = socio;
}
public Libro getLibro() {
	return libro;
}
public void setLibro(Libro libro) {
	this.libro = libro;
}


}
