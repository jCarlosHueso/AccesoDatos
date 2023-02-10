package taller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table
public class Reparacion implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "vehiculo",referencedColumnName = "codigo")
	private Vehiculo vehiculo;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(nullable = true)
	private int tiempo;
	@Column(nullable = false)
	private boolean pagado=false;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clave.reparacion")
	private List<PiezaReparacion> piezasRep = new ArrayList<>();
	
	public Reparacion() {
		
	}

	public Reparacion(int id, Vehiculo vehiculo, Date fecha, int tiempo, boolean pagado) {
		this.id = id;
		this.vehiculo = vehiculo;
		this.fecha = fecha;
		this.tiempo = tiempo;
		this.pagado = pagado;
	}
	
	public void mostrar(boolean mostrarPiezas) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Id: " +id
							+"\tVehiculo: " +vehiculo.getMatricula()
							+"\tFecha: " +formato.format(fecha)
							+"\tTiempo: " +tiempo
							+"\tPagado: " +pagado);
		if(mostrarPiezas) {
			System.out.println("-- DETALLE --");
			for(PiezaReparacion pr:piezasRep) {
				pr.mostrar();
			}
			System.out.println("-- ------------ --");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public List<PiezaReparacion> getPiezasRep() {
		return piezasRep;
	}

	public void setPiezasRep(List<PiezaReparacion> piezasRep) {
		this.piezasRep = piezasRep;
	}
	
	
}
