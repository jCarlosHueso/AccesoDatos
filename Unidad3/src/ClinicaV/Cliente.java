package ClinicaV;

public class Cliente {
	private int codigo;
	private String nombre,email;
	
	
	public Cliente() {
	}


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


	public String getDireccion() {
		return email;
	}


	public void setDireccion(String direccion) {
		this.email = direccion;
	}
	
	
	
	
}
