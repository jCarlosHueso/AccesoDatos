package recuperacionMarzoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import examenRecup.Prestamo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
public class Presupuesto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
private int codigo;
@Column(nullable = false)
@Temporal(TemporalType.DATE)
private Date fecha;
@ManyToOne
@JoinColumn(name="Cliente", referencedColumnName = "nif")

private Cliente cliente;
@OneToMany(cascade=CascadeType.ALL, mappedBy="clave.presupuesto")
List<Detalle_Presupuesto> detalles= new ArrayList<Detalle_Presupuesto>();


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

public List<Detalle_Presupuesto> getDetalles() {
	return detalles;
}

public void setDetalles(List<Detalle_Presupuesto> detalles) {
	this.detalles = detalles;
}

@Override
public String toString() {
	return "Presupuesto [codigo=" + codigo + ", fecha=" + fecha + ", cliente=" + cliente + "]";
}

	
}
