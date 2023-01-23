package clinicaV;

public class Cliente {
	private int codigo;
	private String nombre;
	private String email;
	private String telefono;
	
	
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

	public void mostrar() {
		System.out.println("Codigo:"+codigo + 
				"\tNombre:"+ nombre + 
				"\tDireccion:" + email + 
				"\tTeléfono:"+telefono);
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
