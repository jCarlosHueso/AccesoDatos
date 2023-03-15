package recuperacionMarzoHibernate;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Cliente {
	@Id
private String nif;
	@Column(nullable = false)

private String nombre;
	@Column(nullable = false)

private String telefono;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="codigo")
	List<Presupuesto> detalles= new ArrayList<Presupuesto>();


public Cliente() {

}
public Cliente(String nif, String nombre, String telefono) {
	this.nif = nif;
	this.nombre = nombre;
	this.telefono = telefono;
}
public String getNif() {
	return nif;
}
public void setNif(String nif) {
	this.nif = nif;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
@Override
public String toString() {
	return "Cliente [nif=" + nif + ", nombre=" + nombre + ", telefono=" + telefono + "]";
}


}
