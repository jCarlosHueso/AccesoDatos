package examen;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "cuenta", "nombre", "apellidos", "importe" })
public class Movimiento {
	private String tipo;
	private int cuenta;
	private String nombre, apellidos;
	private float importe;
	
	public Movimiento() {
		super();
	}

	public Movimiento(String tipo, int cuenta, String nombre, String apellidos, float importe) {
		super();
		this.tipo = tipo;
		this.cuenta = cuenta;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.importe = importe;
	}
	@XmlAttribute
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@XmlElement
	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	@XmlElement
	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	
	
}
