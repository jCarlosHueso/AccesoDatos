package instituto;

import java.util.Date;

public class Alumno extends Persona{
	private int numExp;
	private String curso;
	
	
	public Alumno(int id, String nombre, Direccion dir, 
			Date fechaN, int numExp, String curso) {
		super(id, nombre, dir, fechaN);
		this.numExp = numExp;
		this.curso = curso;
		// TODO Auto-generated constructor stub
	}

	public Alumno() {
		super();
	}
	
	public void mostrar() {
		super.mostrar();
		System.out.println("Datos de alumno-NumExp:"+numExp+
				"\tCurso:"+ curso);
	}
	
	public int getNumExp() {
		return numExp;
	}

	public void setNumExp(int numExp) {
		this.numExp = numExp;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	
}