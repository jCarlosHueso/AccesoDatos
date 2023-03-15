package ejercicio1;

import java.util.Date;

public class Presupuesto {
private int codigo;
private Date fecha;
private Cliente cliente;
public Presupuesto() {
}

public Presupuesto(int codigo, Date fecha, Cliente cliente) {
	this.codigo = codigo;
	this.fecha = fecha;
	this.cliente = cliente;
}

public int getCodigo() {
	return codigo;
}

public void setCodigo(int codigo) {
	this.codigo = codigo;
}

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

@Override
public String toString() {
	return "Presupuesto [codigo=" + codigo + ", fecha=" + fecha + ", cliente=" + cliente + "]";
}

	
}
