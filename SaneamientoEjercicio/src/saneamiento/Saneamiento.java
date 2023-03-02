package saneamiento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class Saneamiento implements Serializable{
	@Id
	private String id;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@ManyToOne
	@JoinColumn(name = "veterinario",referencedColumnName = "colegiado")
	private Veterinario veterinario;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "clave.sanea")
	List<ResultadoSaneamiento> resSan = new ArrayList<>();
	
	public Saneamiento() {
		
	}

	public Saneamiento(String id, Date fecha, Veterinario veterinario, List<ResultadoSaneamiento> resSan) {
		this.id = id;
		this.fecha = fecha;
		this.veterinario = veterinario;
		this.resSan = resSan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	public List<ResultadoSaneamiento> getResSan() {
		return resSan;
	}

	public void setResSan(List<ResultadoSaneamiento> resSan) {
		this.resSan = resSan;
	}
	
	public void mostrar(boolean mostrarResSan) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Código: "+id+
				"\tFecha: "+formato.format(fecha)+
				"\tVeteriano: "+veterinario.getColegiado()+"-"+veterinario.getNombre());
		if(mostrarResSan) {
			for(ResultadoSaneamiento rs:resSan) {
				rs.mostrar();
			}	
		}
	}
}
