package examenGuille;

public class Empleado {
	
	private String dni;
	private String nombre;
	private String departamento;
	
	public Empleado() {}

	public Empleado(String dni, String nombre, String departamento) {
		this.dni = dni;
		this.nombre = nombre;
		this.departamento = departamento;
	}
	
	public void mostrar() {
		System.out.println("DNI: " + dni + 
						   "\tNombre: " + nombre + 
						   "\tDepartamento: " + departamento);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
}
