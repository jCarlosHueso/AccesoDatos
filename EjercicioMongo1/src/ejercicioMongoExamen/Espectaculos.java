package ejercicioMongoExamen;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Espectaculos {

	private int codigo;
	private String titulo;
	private Date fecha;
	private String artista;
	private double beneficio;
	
	public Espectaculos() {
		
	}

	public Espectaculos(int id, String titulo, Date fecha, String artista, double beneficio) {
		this.codigo = id;
		this.titulo = titulo;
		this.fecha = fecha;
		this.artista = artista;
		this.beneficio = beneficio;
	}

	public int getId() {
		return codigo;
	}

	public void setId(int id) {
		this.codigo = id;
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

	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Código: "+codigo+"\tTitulo: "+titulo+
				"\tFecha: "+formato.format(fecha)+"\tArtista: "+artista+
				"\tBeneficio: "+beneficio);
	}
}
