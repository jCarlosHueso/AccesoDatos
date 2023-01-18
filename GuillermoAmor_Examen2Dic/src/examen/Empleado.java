package examen;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Empleado {
	
	private int codigo;
	private String dni;
	private String ps;
	private String nombre;
	private Date fechaNac;
	private Departamento departamento;
	private boolean cambiarPs;
	
	public Empleado() {}

	public Empleado(int codigo, String dni, String ps, String nombre, Date fechaNac, Departamento departamento,
			boolean cambiarPs) {
		this.codigo = codigo;
		this.dni = dni;
		this.ps = ps;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.departamento = departamento;
		this.cambiarPs = cambiarPs;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Código: " + codigo + 
				"\tDNI: " + dni + 
				"\tNombre: " + nombre + 
				"\tFecha nac: " + formato.format(fechaNac) + 
				"\tCódigo dep: " + departamento.getNumero() + 
				", Dep: " + departamento.getNombre() + 
				"\tCambiarPs: " + cambiarPs);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public boolean isCambiarPs() {
		return cambiarPs;
	}

	public void setCambiarPs(boolean cambiarPs) {
		this.cambiarPs = cambiarPs;
	}
	
}
