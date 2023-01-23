package clinicaV;

import java.util.ArrayList;

public class Mascota {
	private int codigo;
	private Cliente cliente;
	private String nombre;
	private String tipo;
	private ArrayList<Consulta> consultas=new ArrayList();
	
	public Mascota() {
	}
	public Mascota(int codigo, Cliente cliente, String nombre, String tipo, ArrayList<Consulta> tratamientos) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.nombre = nombre;
		this.tipo = tipo;
		this.consultas = tratamientos;
	}
	
	public void mostrar(boolean mosrtarTrat) {
		System.out.println("Codigo:"+codigo + 
				"\tCliente:"+ cliente.getNombre() +
				"\tNombre:" + nombre +
				"\ttipo:"+tipo);
		if(mosrtarTrat) {
			for(Consulta t: consultas) {
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
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
	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(ArrayList<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	
}
