package Spotifly;

public class Album{
	private int id;
	private String titulo;
	private Artista artista;
	private int anio;

	public Album() {
	
	}

	public Album(int id, String titulo, Artista artista, int anio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.artista = artista;
		this.anio = anio;
	}

	public void mostrar() {		
		System.out.println("Álbum Id:"+id+
				"\tTítilo:"+titulo+
				"\tArtista:"+ artista.getNombre() +
				"\tAño:"+ anio);
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

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

}
