package examen;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"cantidad", "total"})
public class Venta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845532018876315930L;
	private int codigo;
	private int cantidad;
	private float total;
	
	
	public Venta() {}
	
	public Venta(int codigo, int cantidad, float total) {
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.total = total;
	}
	
	public void mostrar() {
		System.out.println("Codigo: "+ codigo +
						   "\tCantidad: "+ cantidad +
						   "\tTotal: "+ total);
	}
	
	@XmlAttribute(name = "producto")
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@XmlElement
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@XmlElement(name = "precio")
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
}
