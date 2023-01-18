package Spotifly;

public class Cancion {
	private String titulo;
	private double valoracion;
	
	
	public void mostrar() {
		System.out.println("Título:" + titulo + 				
				"\tValoración:" + valoracion);
	}
	
	public Cancion() {
		
	}


	public Cancion(String titulo, double valoracion) {
		this.titulo = titulo;
		this.valoracion = valoracion;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public double getValoracion() {
		return valoracion;
	}


	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}
	
	
}
