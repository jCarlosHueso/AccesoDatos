package repasoMongo;

import java.util.ArrayList;

public class Zonas {
	
	private int id;
	private String nombre;
	private float precioBase;
	private ArrayList<Entradas> entradas= new ArrayList<>();
	
	public Zonas() {
		
	}

	public Zonas(int id, String nombre, float precioBase,ArrayList<Entradas>entradas) {
		this.id = id;
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.entradas=entradas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}
	
	public ArrayList<Entradas> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Entradas> entradas) {
		this.entradas = entradas;
	}

	public void mostrar(boolean mostrarEnt) {
		System.out.println("Código: "+id+"\tNombre: "+nombre+"\tPrecioBase: "+precioBase);
		if(mostrarEnt) {
			for(Entradas e:entradas) {
				e.mostrar();
			}
		}
	}
}
