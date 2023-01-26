package clinicaV;

<<<<<<< HEAD
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="cliente")
//Atributo name es para indicar el nombre de la tabla
//Si es el mismo se puede obviar
public class Cliente {
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
	public ArrayList<Mascota> getMascotas() {
		return mascotas;
	}


	public void setMascotas(ArrayList<Mascota> mascotas) {
		this.mascotas = mascotas;
	}


	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cliente" )
	private ArrayList<Mascota> mascotas = new  ArrayList();
=======
public class Cliente {
	private int codigo;
	private String nombre;
	private String email;
	private String telefono;
>>>>>>> d7be6f3fcc409e5f3eeb7cc9918452a8311da14c
	
	
	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Cliente() {
	}


	public Cliente(int codigo, String nombre, String email, String telefono) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}

<<<<<<< HEAD
	public void mostrar(boolean mostrarMascotas) {
=======
	public void mostrar() {
>>>>>>> d7be6f3fcc409e5f3eeb7cc9918452a8311da14c
		System.out.println("Codigo:"+codigo + 
				"\tNombre:"+ nombre + 
				"\tDireccion:" + email + 
				"\tTeléfono:"+telefono);
<<<<<<< HEAD
		if(mostrarMascotas) {
			for(Mascota m : mascotas) {
				m.mostrar(false);
			}
		}
=======
>>>>>>> d7be6f3fcc409e5f3eeb7cc9918452a8311da14c
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
