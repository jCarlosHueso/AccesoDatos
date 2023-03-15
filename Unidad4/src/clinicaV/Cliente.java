package clinicaV;

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
//Atributo name es para indicar el nombre de la tabla
//Si es el mismo se puede obviar
public class Cliente implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//Cliente es pk autonumérica
	private int codigo;
	
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String telefono;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente" )
	private List<Mascota> mascotas = new  ArrayList();
	
	
	public Cliente(int codigo, String nombre, String email, String telefono, 
			List<Mascota> mascotas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.mascotas = mascotas;
	}

	public void mostrar(boolean mostrarMascotas) {
		System.out.println("Codigo:"+codigo + 
				"\tNombre:"+ nombre + 
				"\tDireccion:" + email + 
				"\tTeléfono:"+telefono);
		if(mostrarMascotas) {
			for(Mascota m : mascotas) {
				System.out.println("\n--- MASCOTAS ---");
				m.mostrar(false);
				System.out.println("--- -------- ---\n");
			}
		}
	}
	public List<Mascota> getMascotas() {
		return mascotas;
	}


	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}


	
	
	
	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Cliente() {
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
