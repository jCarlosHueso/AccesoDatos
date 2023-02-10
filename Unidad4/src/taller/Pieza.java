package taller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pieza")
public class Pieza implements Serializable{	
	@Id
	private String codigo;
	@Column(nullable = false)
	private String clase;
	@Column(nullable = false)
	private String descripcion;
	@Column(nullable = false)
	private float precio;
	@Column(nullable = false)
	private int stock;

	//¿Qué campo de PiezaReparacion tiene el pieza?
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clave.pieza")	
	private List<PiezaReparacion> piezasRep = new ArrayList<>();
	
	public Pieza() {
		
	}

	public Pieza(String codigo, String clase, String descripcion, float precio, int stock) {
		this.codigo = codigo;
		this.clase = clase;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}
	
	public void mostrar() {
		System.out.println("Codigo: " +codigo
							+"\tClase: " +clase
							+"\tDescripcion: " +descripcion
							+"\tPrecio: " +precio
							+"\tStock: " +stock);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<PiezaReparacion> getPiezasRep() {
		return piezasRep;
	}

	public void setPiezasRep(List<PiezaReparacion> piezasRep) {
		this.piezasRep = piezasRep;
	}
	
	
}
