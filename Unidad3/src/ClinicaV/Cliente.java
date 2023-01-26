package ClinicaV;

public class Cliente {
	private int codigo;
	private String nombre,email;
	
	
	public Cliente() {
	}


	public Cliente(int codigo, String nombre, String email) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = email;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
