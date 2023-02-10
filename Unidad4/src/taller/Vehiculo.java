package taller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Vehiculo implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Column(nullable = false)
	private String nombrePropietario;
	@Column(nullable = false, unique = true) //Es clave alternativa
	private String matricula;
	@Column(nullable = false)
	private String color;
	@Column(nullable = false)
	private String telefono;
	@Column(nullable = false)
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vehiculo")
	List<Reparacion> reparaciones = new ArrayList<>();
	
	public Vehiculo() {
		
	}

	public Vehiculo(int codigo, String nombrePropietario, String matricula, String color, String telefono,
			String email) {
		this.codigo = codigo;
		this.nombrePropietario = nombrePropietario;
		this.matricula = matricula;
		this.color = color;
		this.telefono = telefono;
		this.email = email;
	}
	
	public void mostrar(boolean mostrarReparaciones) {
		System.out.println("Codigo: " +codigo
							+"\tNombre Propietario: " +nombrePropietario
							+"\tMatricula: " +matricula
							+"\tColor: " +color
							+"\tTelefono: " +telefono
							+"\tEmail: " +email);
	if(mostrarReparaciones) {
		System.out.println("-- REPARACIONES --");
		for(Reparacion r:reparaciones) {
			r.mostrar(false);
		}
		System.out.println("-- ------------ --");
	}
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}
	
	
}
