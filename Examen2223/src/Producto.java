
public class Producto {
	int id;
	String nombre;
	int stock;
	
	public void mostrar()
	{
		System.out.println("Producto:" + id + 
				"\tNombre:"+nombre+
				"\tStock:"+stock);
	}
	public Producto(int id, String nombre, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
	}
	public Producto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
