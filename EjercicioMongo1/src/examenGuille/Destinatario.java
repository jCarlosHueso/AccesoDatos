package examenGuille;

public class Destinatario {
	
	private String empleado;
	private boolean leido;
	
	public Destinatario() {}

	public Destinatario(String empleado, boolean leido) {
		this.empleado = empleado;
		this.leido = leido;
	}
	
	public void mostrar() {
		System.out.println("DNI Empleado: " + empleado + "\tLeído: " + leido);
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	
}
