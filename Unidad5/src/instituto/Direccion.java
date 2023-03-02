package instituto;

public class Direccion {
	private String tipoVia, nombre;
	private int cp;
	
	public Direccion() {
	}

	public Direccion(String tipoVia, String nombre, int cp) {
		this.tipoVia = tipoVia;
		this.nombre = nombre;
		this.cp = cp;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}
	
	
}
