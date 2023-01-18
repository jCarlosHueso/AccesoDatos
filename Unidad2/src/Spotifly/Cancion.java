package Spotifly;




public class Cancion{
	private String titulo;
	private Album album;
	private float valoracion;
		
	public void mostrar() {
		System.out.println("Título:" + titulo + 
				"\tÁlbum:" + album.getTitulo() +
				"\tArtista:" + album.getArtista().getNombre()+ 
				"\tValoración:" + valoracion);
	}

	public Cancion() {

	}

	public Cancion(String titulo, Album album, float valoracion) {

		this.titulo = titulo;
		this.album = album;
		this.valoracion = valoracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public float getValoracion() {
		return valoracion;
	}

	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}
	
	
		
}
