package repasoMongo;

public class Entradas {
	
	private int id;
	private Espectaculos espectaculo;
	private Zonas zona;
	private float precio;
	
	public Entradas() {
		
	}
	public Entradas(int id, Espectaculos espectaculo, Zonas zona, float precio) {
		this.id = id;
		this.espectaculo = espectaculo;
		this.zona = zona;
		this.precio = precio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Espectaculos getEspectaculo() {
		return espectaculo;
	}
	public void setEspectaculo(Espectaculos espectaculo) {
		this.espectaculo = espectaculo;
	}
	public Zonas getZona() {
		return zona;
	}
	public void setZona(Zonas zona) {
		this.zona = zona;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public void mostrar() {
		System.out.println("Código: "+id+"\tEspectáculo: "+espectaculo.getId()+"\tZona: "+zona.getId()+"\tPrecio: "+precio);
	}
}
