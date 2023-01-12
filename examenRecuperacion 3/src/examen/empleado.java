package examen;

import java.io.Serializable;

public class empleado implements Serializable {

	private int numEmp;
	private String dni;
	private String nombre;
	private String departamento;

	public empleado() {
		super();
	}

	public int getNumEmp() {
		return numEmp;
	}

	public void setNumEmp(int numEmp) {
		this.numEmp = numEmp;
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

	public empleado(int numEmp, String dni, String nombre, String departamento) {
		super();
		this.numEmp = numEmp;
		this.dni = dni;
		this.nombre = nombre;
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "empleado [numEmp=" + numEmp + ", dni=" + dni + ", nombre=" + nombre + ", departamento=" + departamento
				+ "]";
	}

}
