package ejercicioMongoExamen;

public class Entradas {

	private int codigo;
	private int espectaculo;
	private int zonas;
	private double precio;

	public Entradas() {
		
	}

	public Entradas(int codigo,int espectaculo, int zonas, double precio) {
		this.codigo = codigo;
		this.espectaculo = espectaculo;
		this.zonas = zonas;
		this.precio = precio;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(int espectaculo) {
		this.espectaculo = espectaculo;
	}

	public int getZonas() {
		return zonas;
	}

	public void setZonas(int zonas) {
		this.zonas = zonas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void mostrar() {
		System.out.println("Código: "+codigo+"\tPrecio: "+precio);
	}
}
