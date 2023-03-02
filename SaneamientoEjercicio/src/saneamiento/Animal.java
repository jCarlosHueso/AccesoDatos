package saneamiento;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class Animal implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaN;
	@Column(nullable = false)
	private String raza;
	@Column(nullable = false)
	private boolean bloqueado;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "clave.animal")
	List<ResultadoSaneamiento> resSan = new ArrayList<>();
	
	public Animal() {
		
	}

	public Animal(int codigo, String nombre, Date fechaN, String raza, boolean bloqueado,ArrayList<ResultadoSaneamiento>rs) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaN = fechaN;
		this.raza = raza;
		this.bloqueado = false;
		this.resSan = rs;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaN() {
		return fechaN;
	}

	public void setFechaN(Date fechaN) {
		this.fechaN = fechaN;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public List<ResultadoSaneamiento> getResSan() {
		return resSan;
	}

	public void setResSan(List<ResultadoSaneamiento> resSan) {
		this.resSan = resSan;
	}
	
	public void mostrar(boolean mostrarResSan) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Código: "+codigo+
				"\tNombre: "+nombre+
				"\tFecha: "+formato.format(fechaN)+
				"\tRaza: "+raza+
				"\tBloqueado: "+bloqueado);
		if(mostrarResSan) {
			for(ResultadoSaneamiento rs:resSan) {
				rs.mostrar();
			}	
		}
	}
}
