package ClinicaV;

public class Cliente {
	private int codigo;
	private String nombre,email;
	
	
	public Cliente() {
	}


<<<<<<< HEAD
	public Cliente(int codigo, String nombre, String email) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = email;
=======
>>>>>>> 80c88f51dc096720268b555a9f5208a96aadde1d
	public Cliente(int codigo, String nombre, String direccion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = direccion;
	}

	public void mostrar() {
		System.out.println("Codigo:"+codigo + 
				"\tNombre:"+ nombre + 
				"\tDireccion:" + email);
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


<<<<<<< HEAD
	public String getEmail() {
=======
>>>>>>> 80c88f51dc096720268b555a9f5208a96aadde1d
	public String getDireccion() {
		return email;
	}


<<<<<<< HEAD
	public void setEmail(String email) {
		this.email = email;
=======
>>>>>>> 80c88f51dc096720268b555a9f5208a96aadde1d
	public void setDireccion(String direccion) {
		this.email = direccion;
	}
	
	
	
	
}
