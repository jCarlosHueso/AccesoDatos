package examen19_12;

public class Servicio {

	
	private int idServicio;
	private String nombre;
	private float precio;
	public Servicio() {
		super();
	}
	public Servicio(int idServicio, String nombre, float precio) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.precio = precio;
	}
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Servicio [idServicio=" + idServicio + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
}
