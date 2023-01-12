package examenRecu;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"matricula","hora","velocidad"})
public class multa {

	private String matricula;
	private String hora;
	private int velocidad;
	public multa() {
		super();
	}
	public multa(String matricula, String hora, int velocidad) {
		super();
		this.matricula = matricula;
		this.hora = hora;
		this.velocidad = velocidad;
	}
	@XmlAttribute 
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	@XmlElement
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	

}
