package repasoMongo;

import java.util.Date;

public class Espectaculos {
	
	private int id;
	private String titulo;
	private Date fecha;
	private String artista;
	private float beneficio;
	
	public Espectaculos() {
		
	}

	public Espectaculos(int id, String titulo, Date fecha, String artista, float beneficio) {
		this.id = id;
		this.titulo = titulo;
		this.fecha = fecha;
		this.artista = artista;
		this.beneficio = beneficio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public float getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(float beneficio) {
		this.beneficio = beneficio;
	}
	
	public void mostrar() {
		System.out.println("Id: "+id+"\tTítutlo: "+titulo+"\tFecha: "+fecha+"\tArtista: "+artista+"\tBeneficio: "+beneficio);
	}
}
