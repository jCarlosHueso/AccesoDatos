package instituto;

import java.util.Date;

public class Profesor extends Persona{
	private String departamento;
	private float salario;
	
	public Profesor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Profesor(int id, String nombre, Direccion dir, Date fechaN,
			String departamento, float salario) {
		super(id, nombre, dir, fechaN);
		this.departamento = departamento;
		this.salario = salario;
		// TODO Auto-generated constructor stub
	}
	public void mostrar() {
		super.mostrar();
		System.out.println("Datos de profesor-Departamento:"+departamento+
				"\tSalario:"+ salario);
	}
	
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	
}