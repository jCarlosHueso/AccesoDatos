package Spotifly;

public class Cancion {
	private String titulo;
	private float valoracion;
	
	
	public void mostrar() {
		System.out.println("Título:" + titulo + 				
				"\tValoración:" + valoracion);
	}
	
	public Cancion() {
		
	}


	public Cancion(String titulo, float valoracion) {
		this.titulo = titulo;
		this.valoracion = valoracion;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public float getValoracion() {
		return valoracion;
	}


	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}
	
	
}
