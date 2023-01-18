package gimnasio;

public class Actividad {
	private int id;
	private String nombre;
	private float coste;
	private String activa;
	
	public Actividad() {}
	
	public Actividad(int id, String nombre, float coste, String activa) {
		this.id = id;
		this.nombre = nombre;
		this.coste = coste;
		this.activa = activa;
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

	public float getCoste() {
		return coste;
	}

	public void setCoste(float coste) {
		this.coste = coste;
	}

	public String getActiva() {
		return activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}
	
	
	public void mostrar() {
		System.out.println("\tID: "+this.id+"\tNOMBRE: "
				+this.nombre+"\tCOSTE: "+this.coste
				+"\tACTIVA: "+this.activa);
	}
	
	
}
