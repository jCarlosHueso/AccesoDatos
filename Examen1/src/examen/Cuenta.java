package examen;

import java.io.Serializable;

public class Cuenta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1438094737076772425L;
	private int codigo;//4 B
	private String apellidos, nombre;//40 B y 20 B
	private float saldo;//4 B
	private boolean cancelada=false; //1 B
	
	public Cuenta() {
		
	}
	public Cuenta(int codigo, String apellidos, String nombre, float saldo, boolean cancelada) {
		super();
		this.codigo = codigo; 
		this.apellidos = apellidos; 
		this.nombre = nombre;
		this.saldo = saldo;
		this.cancelada = cancelada;
	}

	public void mostrar() {
		System.out.println("Cuenta:" +codigo +
				"\tTitular:"+nombre+" "+apellidos+
				"\tSaldo:" + saldo + 
				"\tCancelada:"+cancelada);
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public boolean isCancelada() {
		return cancelada;
	}
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	
	
}
