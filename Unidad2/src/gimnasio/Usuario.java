package gimnasio;

public class Usuario {
	private String id;
	private String tipo;

	public Usuario(String id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	public Usuario() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
