package examenRecu;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement
@XmlType(propOrder = {"via","km","maximo","multas"})
public class radar {
	private String via;
	private int km;
	private int maximo;
	private multa multas;

	public radar() {
	}

	public radar(String via, int km, int maximo, multa multas) {
		this.via = via;
		this.km = km;
		this.maximo = maximo;
		this.multas = multas;
	}
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}
	public int getkm() {
		return km;
	}

	public void setkm(int km) {
		this.km = km;
	}
	@XmlElement
	public int getMaximo() {
		return maximo;
	}
	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}
@XmlElementWrapper
@XmlElement(name="multas")
	public multa getMulta() {
		return multas;
	}

	public void setMulta(multa multas) {
		this.multas = multas;
	}

	
	}
	

