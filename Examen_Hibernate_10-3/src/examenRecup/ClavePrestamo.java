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
private int socio;
	
	@ManyToOne
	@JoinColumn(name="libro", referencedColumnName = "isbn")
	
private String libro;
	
	
	
	
public ClavePrestamo() {
}
public ClavePrestamo(Date fechaP, int socio, String libro) {
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
public int getSocio() {
	return socio;
}
public void setSocio(int socio) {
	this.socio = socio;
}
public String getLibro() {
	return libro;
}
public void setLibro(String libro) {
	this.libro = libro;
}


}
