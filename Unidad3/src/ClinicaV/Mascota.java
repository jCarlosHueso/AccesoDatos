package ClinicaV;

import java.util.ArrayList;

public class Mascota {
	private int codigo, cliente;
	private String nombre, tipo;
	private ArrayList<Tratamiento> tratamientos=new ArrayList();
	public Mascota() {
	}
	public Mascota(int codigo, int cliente, String nombre, String tipo, ArrayList<Tratamiento> tratamientos) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.nombre = nombre;
		this.tipo = tipo;
		this.tratamientos = tratamientos;
	}
	
	private void mostrar(boolean mosrtarTrat) {
		System.out.println("Codigo:"+codigo + 
				"\tCliente:"+ cliente +
				"\tNombre:" + nombre +
				"\ttipo:"+tipo);
		if(mosrtarTrat) {
			for(Tratamiento t: tratamientos) {
				t.mostrar();
			}
		}
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public ArrayList<Tratamiento> getTratamientos() {
		return tratamientos;
	}
	public void setTratamientos(ArrayList<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}
	
	
}
