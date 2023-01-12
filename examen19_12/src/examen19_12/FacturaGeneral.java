package examen19_12;

import java.util.Date;

public class FacturaGeneral {

	private int idf;
	private Date fechad;
	private int cliente;
	
	
	public FacturaGeneral() {
		super();
	}


	public FacturaGeneral(int idf, Date fechad, int cliente) {
		super();
		this.idf = idf;
		this.fechad = fechad;
		this.cliente = cliente;
	}


	public int getIdf() {
		return idf;
	}


	public void setIdf(int idf) {
		this.idf = idf;
	}


	public Date getFechad() {
		return fechad;
	}


	public void setFechad(Date fechad) {
		this.fechad = fechad;
	}


	public int getCliente() {
		return cliente;
	}


	public void setCliente(int cliente) {
		this.cliente = cliente;
	}


	@Override
	public String toString() {
		return "FacturaGeneral [idf=" + idf + ", fechad=" + fechad + ", cliente=" + cliente + "]";
	}
	
	
	
}
