package examen;

public class Producto {
	
	private int codigo; // 4 B
	private float precio; // 4 B
	private int stock; // 4 B
	private String nombre; // 200 B
	
	public Producto() {}
	public Producto(int codigo, float precio, int stock, String nombre) {
		super();
		this.codigo = codigo;
		this.precio = precio;
		this.stock = stock;
		this.nombre = nombre;
	}
	
	public void mostrar() {
		System.out.println("Código: "+codigo
						  +"\tPrecio: "+precio
						  +"\tStock: "+stock
						  +"\tNombre: "+nombre);
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
